package com.Event_Booking.event_booking.DTO.Orders;

import com.Event_Booking.event_booking.DTO.ShipperProfile.ShipperSummaryResponseDTO;
import com.Event_Booking.event_booking.enums.OrderStatus;
import com.Event_Booking.event_booking.enums.PaymentMethod;
import com.Event_Booking.event_booking.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data@Builder
public class OrdersResponseDTO {
    private UUID id;
    private String orderCode;

    // Thông tin nhà hàng tối giản
    private UUID restaurantId;
    private String restaurantName;
    private String restaurantPhone;

    // Thông tin customer tối giản (dùng khi nhà hàng/shipper xem)
    private UUID customerId;
    private String customerName;
    private String customerPhone;

    private String deliveryAddress;
    private BigDecimal deliveryLatitude;
    private BigDecimal deliveryLongitude;

    private OrderStatus status;
    private BigDecimal subtotal;
    private BigDecimal deliveryFee;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private String note;

    private Instant estimatedDeliveryAt;
    private Instant confirmedAt;
    private Instant deliveredAt;
    private Instant cancelledAt;
    private String cancelReason;
    private Instant createdAt;

    private List<OderItemsResponseDTO> items;

    // Thông tin thanh toán
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    // Thông tin shipper (null khi chưa assign)
    private ShipperSummaryResponseDTO shipper;
}
