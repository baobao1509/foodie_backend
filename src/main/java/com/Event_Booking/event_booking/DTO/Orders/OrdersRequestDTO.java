package com.Event_Booking.event_booking.DTO.Orders;

import com.Event_Booking.event_booking.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class OrdersRequestDTO {
    @NotNull(message = "restaurantId không được để trống")
    private UUID restaurantId;

    // ID địa chỉ đã lưu, hoặc null nếu nhập địa chỉ mới
    private UUID deliveryAddressId;

    // Nhập thẳng địa chỉ nếu không dùng địa chỉ đã lưu
    private String deliveryAddress;

    @NotEmpty(message = "Đơn hàng phải có ít nhất 1 món")
    private List<OrderItemsRequestDTO> items;

    private String note;

    @NotNull(message = "Phương thức thanh toán không được để trống")
    private PaymentMethod paymentMethod;

    // Mã khuyến mãi (tuỳ chọn)
    private String promotionCode;
}
