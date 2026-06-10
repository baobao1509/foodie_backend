package com.Event_Booking.event_booking.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "restaurant_balances")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RestaurantBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    // 1 restaurant : 1 balance
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false, unique = true)
    private Restaurant restaurant;

    // Tổng tiền đã nhận từ đầu (cộng dồn)
    @Column(name = "total_received", nullable = false, precision = 14, scale = 2)
    @Builder.Default
    private BigDecimal totalReceived = BigDecimal.ZERO;

    // Tiền chưa trích 20% cho admin (kỳ hiện tại)
    @Column(name = "pending_amount", nullable = false, precision = 14, scale = 2)
    @Builder.Default
    private BigDecimal pendingAmount = BigDecimal.ZERO;

    // Lần trích gần nhất
    @Column(name = "last_settled_at")
    private OffsetDateTime lastSettledAt;

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}