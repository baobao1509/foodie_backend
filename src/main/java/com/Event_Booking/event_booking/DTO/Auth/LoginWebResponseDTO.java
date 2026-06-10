package com.Event_Booking.event_booking.DTO.Auth;

import com.Event_Booking.event_booking.DTO.Users.UserSummaryDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginWebResponseDTO {
    private Long expiresIn;        // giây còn lại của access token
    private UserSummaryDTO user;
}
