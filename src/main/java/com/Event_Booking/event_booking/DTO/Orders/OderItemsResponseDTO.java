package com.Event_Booking.event_booking.DTO.Orders;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data@Builder
public class OderItemsResponseDTO {
    private UUID id;
    private UUID menuItemId;
    private String menuItemName;    // snapshot — tên lúc đặt
    private String menuItemImage;
    private BigDecimal unitPrice;   // snapshot — giá lúc đặt
    private Integer quantity;
    private BigDecimal subtotal;
    private String optionsSummary;  // "Size: L, Topping: Phô mai"
    private String note;
}
