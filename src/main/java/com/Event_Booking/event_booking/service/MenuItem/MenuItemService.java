package com.Event_Booking.event_booking.service.MenuItem;

import com.Event_Booking.event_booking.DTO.Menu.MenuItemResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuItemService {
    Page<MenuItemResponseDTO> getAllMenuItem(Pageable pageable);
}
