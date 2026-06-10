package com.Event_Booking.event_booking.repository;
import com.Event_Booking.event_booking.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
}
