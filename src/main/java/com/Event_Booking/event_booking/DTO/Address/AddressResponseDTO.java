package com.Event_Booking.event_booking.DTO.Address;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data@Builder
public class AddressResponseDTO {
    private UUID id;
    private String label;
    private String fullAddress;
    private String ward;
    private String district;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean isDefault;
}
