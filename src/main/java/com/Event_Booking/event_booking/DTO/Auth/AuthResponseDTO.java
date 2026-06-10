package com.Event_Booking.event_booking.DTO.Auth;
import com.Event_Booking.event_booking.DTO.Users.UserRegisterDTO;
import com.Event_Booking.event_booking.DTO.Users.UserSummaryDTO;
import lombok.Builder;
import lombok.Data;

// ─── Trả về sau khi đăng nhập / refresh token ────────────────────────────────
@Data @Builder
public class AuthResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType;      // "Bearer"
    private Long expiresIn;        // giây còn lại của access token
    private UserSummaryDTO user;
}

