package com.Event_Booking.event_booking.DTO.Menu;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class MenuItemOptionResponseDTO {
    private UUID id;
    private String groupName;   // "Size", "Topping", "Độ cay"
    private String optionName;  // "Large", "Phô mai", "Cay vừa"
    private Boolean extraPrice;
    private Boolean isDefault;
}
