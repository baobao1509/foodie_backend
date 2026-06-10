package com.Event_Booking.event_booking.DTO.Users;
import com.Event_Booking.event_booking.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

// ─── Thông tin tối giản — nhúng vào các response khác ───────────────────────
// Dùng trong: AuthResponse, RestaurantResponse, ReviewResponse...
@Data @Builder
public class UserSummaryDTO {
    private UUID id;
    private String fullName;
    private String avatarUrl;
    private UserRole role;
    // KHÔNG có: email, phone, passwordHash, status, lastLoginAt
}
