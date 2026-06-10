package com.Event_Booking.event_booking.DTO.ShipperProfile;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShipperLocationRequestDTO {

    @NotNull
    @DecimalMin("-90.0") @DecimalMax("90.0")
    private BigDecimal latitude;

    @NotNull
    @DecimalMin("-180.0") @DecimalMax("180.0")
    private BigDecimal longitude;

    // Độ chính xác GPS từ thiết bị (metres)
    private BigDecimal accuracy;
}
