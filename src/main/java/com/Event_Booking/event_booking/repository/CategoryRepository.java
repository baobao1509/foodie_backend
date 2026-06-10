package com.Event_Booking.event_booking.repository;
import com.Event_Booking.event_booking.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
