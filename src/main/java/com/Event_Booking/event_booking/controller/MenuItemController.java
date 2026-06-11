package com.Event_Booking.event_booking.controller;

import com.Event_Booking.event_booking.DTO.Auth.RegisterRequestDTO;
import com.Event_Booking.event_booking.DTO.Menu.MenuItemResponseDTO;
import com.Event_Booking.event_booking.DTO.Users.UserResponseDTO;
import com.Event_Booking.event_booking.service.MenuItem.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menuItem")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<Page<MenuItemResponseDTO>> getAll(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return ResponseEntity.ok(menuItemService.getAllMenuItem(pageable));
    }
}
