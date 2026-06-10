package com.Event_Booking.event_booking.service.MenuItem;

import com.Event_Booking.event_booking.DTO.Menu.MenuItemResponseDTO;
import com.Event_Booking.event_booking.entity.MenuItem;
import com.Event_Booking.event_booking.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemServiceimpl implements MenuItemService{
    private final MenuItemRepository menuItemRepository;

    @Override
    public List<MenuItemResponseDTO> getAllMenuItem(){
        List<MenuItem> MenuItemList=menuItemRepository.findAll();
    };
}
