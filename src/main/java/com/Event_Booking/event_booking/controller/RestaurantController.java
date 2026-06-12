package com.Event_Booking.event_booking.controller;

import com.Event_Booking.event_booking.DTO.Menu.MenuItemResponseDTO;
import com.Event_Booking.event_booking.DTO.Restaurants.RestaurantsResponseDTO;
import com.Event_Booking.event_booking.service.Restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<Page<RestaurantsResponseDTO>> getAll(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return ResponseEntity.ok(restaurantService.getAllRestaurant(pageable));
    }
}
