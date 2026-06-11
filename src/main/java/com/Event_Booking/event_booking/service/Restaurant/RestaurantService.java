package com.Event_Booking.event_booking.service.Restaurant;

import com.Event_Booking.event_booking.DTO.Restaurants.RestaurantsResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestaurantService {
    public Page<RestaurantsResponseDTO> getAllRestaurant(Pageable pageable);
}
