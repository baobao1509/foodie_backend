package com.Event_Booking.event_booking.entity;

import com.Event_Booking.event_booking.enums.EarningStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipper_earnings", indexes = {
        @Index(name = "idx_shipper_earnings_shipper", columnList = "shipper_id")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ShipperEarning {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipper_id", nullable = false)
    private User shipper;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "delivery_id", nullable = false, unique = true)
    private Delivery delivery;

    // Snapshot km thực tế lúc hoàn thành
    @Column(name = "distance_km", nullable = false, precision = 6, scale = 2)
    private BigDecimal distanceKm;

    // Snapshot đơn giá/km lúc đó — tránh ảnh hưởng khi admin đổi giá sau
    @Column(name = "rate_per_km", nullable = false, precision = 10, scale = 2)
    private BigDecimal ratePerKm;

    // distance_km × rate_per_km
    @Column(name = "earned_amount", nullable = false, precision = 12, scale = 2)
    private BigDecimal earnedAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private EarningStatus status = EarningStatus.PENDING;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();
}