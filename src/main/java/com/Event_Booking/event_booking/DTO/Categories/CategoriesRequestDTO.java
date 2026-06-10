package com.Event_Booking.event_booking.DTO.Categories;

import com.Event_Booking.event_booking.entity.Restaurant;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;
@Data
public class CategoriesRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private Integer displayOrder;

    private UUID restaurantid;
}
