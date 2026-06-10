package com.Event_Booking.event_booking.DTO.Categories;

import com.Event_Booking.event_booking.DTO.Menu.MenuItemResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data@Builder
public class CategoriesResponseDTO {
    private UUID id;
    private String name;
    private Integer displayOrder;
    private List<MenuItemResponseDTO> items;
}
