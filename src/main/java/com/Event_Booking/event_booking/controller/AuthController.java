package com.Event_Booking.event_booking.controller;

import com.Event_Booking.event_booking.DTO.Auth.AuthResponseDTO;
import com.Event_Booking.event_booking.DTO.Auth.LoginRequestDTO;
import com.Event_Booking.event_booking.DTO.Auth.LoginWebResponseDTO;
import com.Event_Booking.event_booking.DTO.Auth.RegisterRequestDTO;
import com.Event_Booking.event_booking.DTO.Users.UserResponseDTO;
import com.Event_Booking.event_booking.entity.User;
import com.Event_Booking.event_booking.exceptions.UnauthorizedException;
import com.Event_Booking.event_booking.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // POST /api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    // POST /api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody LoginRequestDTO request,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        String ip = httpRequest.getRemoteAddr();
        AuthResponseDTO auth = authService.login(request, ip);
        String clientType = httpRequest.getHeader("X-Client-Type");
        if ("web".equals(clientType)) {
            // Set cookie cho web — browser tự lưu, tự gửi
            setCookies(httpResponse, auth);

            // Không trả token trong body — chỉ trả userInfo
            return ResponseEntity.ok(
                    LoginWebResponseDTO.builder()
                            .user(auth.getUser())
                            .expiresIn(auth.getExpiresIn())
                            .build()
            );
        }
        return ResponseEntity.ok(authService.login(request, ip));
    }

    // POST /api/v1/auth/refresh
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            // Mobile gửi refreshToken trong header
            @RequestHeader(value = "X-Refresh-Token", required = false) String headerRefreshToken,
            // Web gửi refreshToken trong cookie
            @CookieValue(value = "refreshToken", required = false) String cookieRefreshToken,
            HttpServletRequest httpRequest,
            HttpServletResponse httpResponse
    ) {
        String ip = httpRequest.getRemoteAddr();

        // Lấy refreshToken từ nguồn nào có
        String refreshToken = headerRefreshToken != null
                ? headerRefreshToken
                : cookieRefreshToken;

        if (refreshToken == null) {
            throw new UnauthorizedException("Không có refresh token");
        }

        AuthResponseDTO auth = authService.refreshToken(refreshToken, ip);
        String clientType = httpRequest.getHeader("X-Client-Type");

        if ("web".equals(clientType)) {
            // Cập nhật cookie mới
            setCookies(httpResponse, auth);
            return ResponseEntity.ok(
                    LoginWebResponseDTO.builder()
                            .user(auth.getUser())
                            .expiresIn(auth.getExpiresIn())
                            .build()
            );
        }

        // Mobile → trả token mới trong body
        return ResponseEntity.ok(auth);
    }

    // POST /api/v1/auth/logout  (logout thiết bị hiện tại)
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "X-Refresh-Token", required = false) String headerRefreshToken,
            @CookieValue(value = "refreshToken", required = false) String cookieRefreshToken,
            HttpServletResponse httpResponse,
            HttpServletRequest httpRequest
    ) {
        String refreshToken = headerRefreshToken != null
                ? headerRefreshToken
                : cookieRefreshToken;

        if (refreshToken != null) {
            authService.logout(refreshToken);
        }

        // Xóa cookie nếu là web
        String clientType = httpRequest.getHeader("X-Client-Type");
        if ("web".equals(clientType)) {
            clearCookies(httpResponse);
        }

        return ResponseEntity.noContent().build();
    }

    // POST /api/v1/auth/logout-all  (logout tất cả thiết bị)
    @PostMapping("/logout-all")
    public ResponseEntity<Void> logoutAll(
            @RequestHeader("Authorization") String authHeader
    ) {
        String accessToken = authHeader.substring(7); // bỏ "Bearer "
        authService.logoutAll(accessToken);
        return ResponseEntity.noContent().build();
    }

    // GET /api/v1/auth/me  (lấy profile của chính mình)
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> me(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .role(user.getRole())
                .status(user.getStatus())
                .emailVerified(user.getEmailVerified())
                .phoneVerified(user.getPhoneVerified())
                .lastLoginAt(Instant.from(user.getLastLoginAt()))
                .createdAt(Instant.from(user.getCreatedAt()))
                .build());
    }




    // ── Helper: set cookie ────────────────────────────────────────────────────
    private void setCookies(HttpServletResponse response, AuthResponseDTO auth) {

        // accessToken cookie — 15 phút
        ResponseCookie accessCookie = ResponseCookie
                .from("accessToken", auth.getAccessToken())
                .httpOnly(true)                      // JS không đọc được → chống XSS
                .secure(true)                        // chỉ gửi qua HTTPS
                .sameSite("Strict")                  // chống CSRF
                .maxAge(auth.getExpiresIn())         // 900 giây
                .path("/")                           // gửi cho mọi request
                .build();

        // refreshToken cookie — 30 ngày, chỉ gửi khi call /auth/refresh
        ResponseCookie refreshCookie = ResponseCookie
                .from("refreshToken", auth.getRefreshToken())
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .maxAge(2592000)                     // 30 ngày
                .path("/api/v1/auth/refresh")        // chỉ gửi đúng endpoint này
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, accessCookie.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, refreshCookie.toString());
    }

    // ── Helper: xóa cookie khi logout ────────────────────────────────────────
    private void clearCookies(HttpServletResponse response) {

        ResponseCookie clearAccess = ResponseCookie
                .from("accessToken", "")
                .httpOnly(true).secure(true)
                .maxAge(0)      // xóa ngay
                .path("/")
                .build();

        ResponseCookie clearRefresh = ResponseCookie
                .from("refreshToken", "")
                .httpOnly(true).secure(true)
                .maxAge(0)
                .path("/api/v1/auth/refresh")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, clearAccess.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, clearRefresh.toString());
    }
}


