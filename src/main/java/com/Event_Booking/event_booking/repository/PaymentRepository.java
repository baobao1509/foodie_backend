package com.Event_Booking.event_booking.repository;
import com.Event_Booking.event_booking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
