package com.Event_Booking.event_booking.DTO.Orders;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderItemsRequestDTO {
    @NotNull(message = "menuItemId không được để trống")
    private UUID menuItemId;

    @Positive(message = "Số lượng phải lớn hơn 0")
    private int quantity;

    // Danh sách option ID đã chọn (Size L, Topping phô mai...)
    private List<UUID> selectedOptionIds;

    private String note;
}
