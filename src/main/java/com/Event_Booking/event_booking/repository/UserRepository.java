package com.Event_Booking.event_booking.repository;

import com.Event_Booking.event_booking.entity.User;
import com.Event_Booking.event_booking.enums.AccountStatus;
import com.Event_Booking.event_booking.enums.UserRole;
import org.aspectj.apache.bcel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);

    User findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
