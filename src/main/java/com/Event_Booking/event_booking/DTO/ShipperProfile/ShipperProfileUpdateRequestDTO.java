package com.Event_Booking.event_booking.DTO.ShipperProfile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShipperProfileUpdateRequestDTO {

    @NotBlank
    private String vehicleType;   // MOTORBIKE | BICYCLE | CAR

    @NotBlank
    private String licensePlate;

    private String idCardNumber;
    private String bankAccount;
    private String bankName;

    // Bật/tắt nhận đơn
    private Boolean isOnline;
}
