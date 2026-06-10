package com.Event_Booking.event_booking.DTO.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MenuItemResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;
    private BigDecimal originalPrice; // null nếu không sale
    private Boolean isAvailable;
    private Boolean isFeatured;
    private Integer displayOrder;

    // Các tuỳ chọn đi kèm (Size, Topping...)
    // Group theo groupName ở frontend
    private List<MenuItemOptionResponseDTO> options;
}
