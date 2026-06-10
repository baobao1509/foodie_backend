package com.Event_Booking.event_booking.DTO.Reviews;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class ReviewResponseDTO {
    private UUID id;
    private UUID orderId;
    private UUID restaurantID;
    private Integer foodRating;
    private Integer deliveryRating;
    private String comment;
    private List<String> imageUrls;
    private Instant createdAt;

    // Nếu isAnonymous = false thì hiện tên, ngược lại null
    private String customerName;
    private String customerAvatar;
}
