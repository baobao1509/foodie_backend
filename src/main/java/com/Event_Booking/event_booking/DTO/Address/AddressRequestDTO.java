package com.Event_Booking.event_booking.DTO.Address;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddressRequestDTO {

    private String label = "Nhà"; // Nhà | Công ty | Trường học...

    @NotBlank(message = "Địa chỉ không được để trống")
    private String fullAddress;

    private String ward;
    private String district;

    @NotBlank(message = "Thành phố không được để trống")
    private String city;

    @DecimalMin("-90.0") @DecimalMax("90.0")
    private BigDecimal latitude;

    @DecimalMin("-180.0") @DecimalMax("180.0")
    private BigDecimal longitude;

    private Boolean isDefault = false;
}
