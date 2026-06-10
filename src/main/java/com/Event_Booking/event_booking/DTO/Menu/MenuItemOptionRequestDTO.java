package com.Event_Booking.event_booking.DTO.Menu;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MenuItemOptionRequestDTO {

    @NotBlank(message = "Tên nhóm không được để trống")
    private String groupName;   // e.g. "Size", "Topping"

    @NotBlank(message = "Tên option không được để trống")
    private String optionName;  // e.g. "Large", "Phô mai"

    @DecimalMin(value = "0.0", message = "Phụ phí không được âm")
    private BigDecimal extraPrice;

    private Boolean isDefault;
}