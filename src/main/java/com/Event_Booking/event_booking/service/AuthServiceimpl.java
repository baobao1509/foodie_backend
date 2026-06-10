package com.Event_Booking.event_booking.service;

import com.Event_Booking.event_booking.DTO.Auth.AuthResponseDTO;
import com.Event_Booking.event_booking.DTO.Auth.LoginRequestDTO;
import com.Event_Booking.event_booking.DTO.Auth.RegisterRequestDTO;
import com.Event_Booking.event_booking.DTO.Users.UserResponseDTO;
import com.Event_Booking.event_booking.DTO.Users.UserSummaryDTO;
import com.Event_Booking.event_booking.entity.RefreshToken;
import com.Event_Booking.event_booking.entity.User;
import com.Event_Booking.event_booking.enums.AccountStatus;
import com.Event_Booking.event_booking.enums.UserRole;
import com.Event_Booking.event_booking.exceptions.BadRequestException;
import com.Event_Booking.event_booking.exceptions.UnauthorizedException;
import com.Event_Booking.event_booking.repository.RefreshTokenRepository;
import com.Event_Booking.event_booking.repository.UserRepository;
import com.Event_Booking.event_booking.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceimpl implements AuthService{
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public UserResponseDTO register(RegisterRequestDTO request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email đã được sử dụng: " + request.getEmail());
        };
        if(userRepository.existsByPhone(request.getPhone())){
            throw new BadRequestException("Phone đã được sử dụng: " + request.getPhone());
        }

        User user = User.builder()
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .role(request.getRole())
                .build();

        // Nhà hàng và shipper cần admin duyệt trước
        if (request.getRole() == UserRole.RESTAURANT || request.getRole() == UserRole.SHIPPER) {
            user.setStatus(AccountStatus.PENDING);
        }

        User saved = userRepository.save(user);
        log.info("New user registered: {} [{}]", saved.getEmail(), saved.getRole());

        return toUserResponse(saved);
    };


    @Override
    @Transactional
    public AuthResponseDTO login(LoginRequestDTO request, String ipAddress){
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        User user = (User) authentication.getPrincipal();
        user.setLastLoginAt(OffsetDateTime.now());

        userRepository.save(user);

        return buildAuthResponse(
                user,
                request.getDeviceInfo(),
                ipAddress
        );
    };

    @Override
    @Transactional
    public AuthResponseDTO refreshToken(String refreshToken, String ipAddress) {

        // Validate JWT signature trước
        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new UnauthorizedException("Refresh token không hợp lệ hoặc đã hết hạn");
        }

        // Kiểm tra trong DB (chưa bị revoke, chưa hết hạn)
        String tokenHash = hashToken(refreshToken);
        RefreshToken storedToken = refreshTokenRepository
                .findByTokenHashAndRevokedFalse(tokenHash)
                .orElseThrow(() -> new UnauthorizedException("Refresh token không tồn tại hoặc đã bị thu hồi"));

        if (storedToken.getExpiresAt().isBefore(OffsetDateTime.from(Instant.now()))) {
            throw new UnauthorizedException("Refresh token đã hết hạn, vui lòng đăng nhập lại");
        }

        // Revoke token cũ (rotation — mỗi lần refresh cấp token mới)
        refreshTokenRepository.revokeByTokenHash(tokenHash);

        User user = storedToken.getUser();
        return buildAuthResponse(user, storedToken.getDeviceInfo(), ipAddress);
    }

    @Override
    @Transactional
    public void logout(String refreshToken) {
        String tokenHash = hashToken(refreshToken);
        refreshTokenRepository.revokeByTokenHash(tokenHash);
        log.info("Token revoked: {}", tokenHash.substring(0, 8) + "...");
    }

    @Override
    @Transactional
    public void logoutAll(String accessToken) {
        UUID userId = jwtUtil.extractUserId(accessToken);
        refreshTokenRepository.revokeAllByUserId(userId);
        log.info("All tokens revoked for user: {}", userId);
    }
    // ─── HELPERS ──────────────────────────────────────────────────────────────
    private AuthResponseDTO buildAuthResponse(User user, String deviceInfo, String ipAddress) {
        // Tạo access token
        String accessToken = jwtUtil.generateAccessToken(user.getId(), user.getEmail(), user.getRole());

        // Tạo refresh token và lưu hash vào DB
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());
        RefreshToken tokenEntity = RefreshToken.builder()
                .user(user)
                .tokenHash(hashToken(refreshToken))
                .deviceInfo(deviceInfo)
                .ipAddress(ipAddress)
                .expiresAt(OffsetDateTime.now())
                .build();
        refreshTokenRepository.save(tokenEntity);

        return AuthResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtUtil.getAccessTokenExpiration())
                .user(UserSummaryDTO.builder()
                        .id(user.getId())
                        .fullName(user.getFullName())
                        .avatarUrl(user.getAvatarUrl())
                        .role(user.getRole())
                        .build())
                .build();
    }
    private UserResponseDTO toUserResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .status(user.getStatus())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .createdAt(Instant.now())
                .build();
    }
    // SHA-256 hash của token — không lưu token gốc vào DB
    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}
