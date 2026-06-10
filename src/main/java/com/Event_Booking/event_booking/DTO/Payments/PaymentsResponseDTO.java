package com.Event_Booking.event_booking.DTO.Payments;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data@Builder
public class PaymentsResponseDTO {
    private UUID id;

}
