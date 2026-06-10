package com.Event_Booking.event_booking.DTO.Notifications;

import com.Event_Booking.event_booking.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class NotificationsResponseDTO {
    private UUID id;
    private NotificationType type;
    private String title;
    private String body;
    private Map<String, Object> data; // { "orderId": "...", "restaurantId": "..." }
    private Boolean isRead;
    private Instant createdAt;
}
