package com.Event_Booking.event_booking.DTO.Promotions;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
public class PromotionsRequestDTO {

    // null = khuyến mãi toàn platform (chỉ ADMIN mới tạo được)
    // UUID = khuyến mãi của riêng nhà hàng
    private UUID restaurantId;

    @NotBlank
    @Size(max = 50)
    private String code;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String discountType; // PERCENT | FIXED

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal discountValue;

    @DecimalMin("0.0")
    private BigDecimal minOrderValue;

    // Giới hạn giảm tối đa (chỉ dùng khi discountType = PERCENT)
    private BigDecimal maxDiscountAmount;

    private Integer maxUsage;          // null = không giới hạn
    private Integer maxUsagePerUser;

    @NotNull
    private Instant validFrom;

    @NotNull
    private Instant validUntil;
}
