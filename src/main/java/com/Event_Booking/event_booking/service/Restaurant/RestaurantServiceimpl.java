package com.Event_Booking.event_booking.service.Restaurant;

import com.Event_Booking.event_booking.DTO.Restaurants.RestaurantsResponseDTO;
import com.Event_Booking.event_booking.entity.MenuItem;
import com.Event_Booking.event_booking.entity.Restaurant;
import com.Event_Booking.event_booking.enums.AccountStatus;
import com.Event_Booking.event_booking.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RestaurantServiceimpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;

    @Override
    public Page<RestaurantsResponseDTO> getAllRestaurant(Pageable pageable){

        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        return  restaurantPage.map(item -> RestaurantsResponseDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .slug(item.getSlug())
                .description(item.getDescription())
                .coverImageUrl(item.getCoverImageUrl())
                .phone(item.getPhone())
                .email(item.getEmail())
                .fullAddress(item.getFullAddress())
                .ward(item.getWard())
                .district(item.getDistrict())
                .city(item.getCity())
                .latitude(item.getLatitude())
                .longitude(item.getLongitude())
                .openingTime(item.getOpeningTime())
                .closingTime(item.getClosingTime())
                .minOrderValue(item.getMinOrderValue())
                .deliveryFee(item.getDeliveryFee())
                .rating(item.getRating())
                .totalReviews(item.getTotalReviews())
                .isOpen(item.getIsOpen())
                .status(item.getStatus())
                .createdAt(item.getCreatedAt())
                .ownerId(item.getOwner().getId())
                .ownerName(item.getOwner().getFullName())
                .ownerPhone(item.getOwner().getPhone())
                .build());
    }
}
