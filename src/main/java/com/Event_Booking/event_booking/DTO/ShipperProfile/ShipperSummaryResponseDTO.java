package com.Event_Booking.event_booking.DTO.ShipperProfile;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data @Builder
public class ShipperSummaryResponseDTO {
    private UUID id;
    private String fullName;
    private String avatarUrl;
    private String phone;           // để customer gọi trực tiếp
    private String vehicleType;
    private String licensePlate;
    private BigDecimal rating;

    // Vị trí hiện tại — lấy từ Redis cache, null nếu shipper offline
    private BigDecimal currentLatitude;
    private BigDecimal currentLongitude;
}
