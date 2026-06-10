package com.Event_Booking.event_booking.repository;

import com.Event_Booking.event_booking.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
