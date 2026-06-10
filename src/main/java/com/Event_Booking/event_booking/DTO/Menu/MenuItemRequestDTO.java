package com.Event_Booking.event_booking.DTO.Menu;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
public class MenuItemRequestDTO {

    @NotNull(message = "restaurantId không được để trống")
    private UUID restaurantId;

    private UUID categoryId; // null = chưa phân loại

    @NotNull(message = "Tên món không được để trống")
    @Size(max = 200)
    private String name;

    private String description;
    private String imageUrl;

    @NotNull(message = "Giá không được để trống")
    @DecimalMin(value = "0.0", message = "Giá không được âm")
    private BigDecimal price;

    // Giá gốc — truyền lên khi đang sale, null nếu không có
    private BigDecimal originalPrice;

    private Boolean isAvailable;
    private Boolean isFeatured;
    private Integer displayOrder;

    // Danh sách tuỳ chọn đi kèm (Size, Topping...)
    private List<MenuItemOptionRequestDTO> options;
}
