package com.Event_Booking.event_booking.DTO.Restaurants;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.Event_Booking.event_booking.enums.AccountStatus;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantsResponseDTO {

    private UUID id;
    private String name;
    private String slug;
    private String description;
    private String coverImageUrl;
    private String phone;
    private String email;
    private String fullAddress;
    private String ward;
    private String district;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private BigDecimal minOrderValue;
    private BigDecimal deliveryFee;
    private BigDecimal rating;
    private Integer totalReviews;
    private Boolean isOpen;
    private AccountStatus status;
    private OffsetDateTime createdAt;

    // Chỉ id + tên của owner — KHÔNG phải cả object User
    private UUID ownerId;
    private String ownerName;
    private String ownerPhone;

}
