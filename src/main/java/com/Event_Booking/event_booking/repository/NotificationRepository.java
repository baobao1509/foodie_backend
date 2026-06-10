package com.Event_Booking.event_booking.repository;
import com.Event_Booking.event_booking.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
