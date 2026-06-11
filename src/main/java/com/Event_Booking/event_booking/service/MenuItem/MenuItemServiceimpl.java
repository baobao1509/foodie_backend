package com.Event_Booking.event_booking.service.MenuItem;

import com.Event_Booking.event_booking.DTO.Menu.MenuItemResponseDTO;
import com.Event_Booking.event_booking.entity.MenuItem;
import com.Event_Booking.event_booking.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemServiceimpl implements MenuItemService{
    private final MenuItemRepository menuItemRepository;

    @Override
    public Page<MenuItemResponseDTO> getAllMenuItem(Pageable pageable){
        Page<MenuItem> menuItemPage = menuItemRepository.findAll(pageable);

        return menuItemPage.map(item -> MenuItemResponseDTO.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .description(item.getDescription())
                .imageUrl(item.getImageUrl())
                .originalPrice(item.getOriginalPrice())
                .isAvailable(item.getIsAvailable())
                .isFeatured(item.getIsFeatured())
                .displayOrder(item.getDisplayOrder())
                .build());
    };
}
