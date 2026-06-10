package com.Event_Booking.event_booking.DTO.Payments;

import com.Event_Booking.event_booking.enums.PaymentMethod;
import com.Event_Booking.event_booking.enums.PaymentStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentsRequestDTO {
    @NotNull
    private UUID id;
    @NotNull
    private UUID oder_id;
    @NotNull
    private PaymentMethod method;
    @NotNull
    private PaymentStatus status;
    @DecimalMin("0.0")
    @NotNull
    private BigDecimal amount;
    private String transaction_id;
    private String gateway_response;
}
