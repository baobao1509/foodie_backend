package com.Event_Booking.event_booking.DTO.Users;

import com.Event_Booking.event_booking.enums.AccountStatus;
import com.Event_Booking.event_booking.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;
@Data @Builder
public class UserResponseDTO {
    Instant now = Instant.now();
    private UUID id;
    private String email;
    private String phone;
    private String fullName;
    private String avatarUrl;
    private UserRole role;
    private AccountStatus status;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private Instant lastLoginAt;
    private Instant createdAt;
}
