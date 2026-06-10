package com.Event_Booking.event_booking.DTO.Promotions;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
@Data
@Builder
public class PromotionsResponseDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private String discountType;       // PERCENT | FIXED
    private BigDecimal discountValue;
    private BigDecimal minOrderValue;
    private BigDecimal maxDiscountAmount;
    private Integer maxUsage;
    private Integer usedCount;
    private Integer maxUsagePerUser;
    private Instant validFrom;
    private Instant validUntil;
    private Boolean isActive;

    // null = platform-wide, có giá trị = của nhà hàng cụ thể
    private UUID restaurantId;
    private String restaurantName;
}
