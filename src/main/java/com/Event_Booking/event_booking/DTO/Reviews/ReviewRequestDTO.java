package com.Event_Booking.event_booking.DTO.Reviews;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

// ─── Viết đánh giá sau khi nhận hàng ─────────────────────────────────────────
@Data
public class ReviewRequestDTO {
    // orderId lấy từ path variable — không cần truyền trong body

    @NotNull
    @Min(1) @Max(5)
    private Integer foodRating;

    @Min(1) @Max(5)
    private Integer deliveryRating; // null nếu không muốn đánh giá shipper

    @Size(max = 1000)
    private String comment;

    private java.util.List<String> imageUrls;

    private Boolean isAnonymous = false;
}
