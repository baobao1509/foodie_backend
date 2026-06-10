package com.Event_Booking.event_booking.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "shipper_locations", indexes = {
        @Index(name = "idx_shipper_locations_shipper_time",
                columnList = "shipper_id, recorded_at DESC")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ShipperLocation {

    // Dùng Long thay UUID vì insert rất nhiều (GPS liên tục)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipper_id", nullable = false)
    private User shipper;

    @Column(nullable = false, precision = 10, scale = 8)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 11, scale = 8)
    private BigDecimal longitude;

    // Độ chính xác GPS từ thiết bị (metres)
    @Column(precision = 6, scale = 2)
    private BigDecimal accuracy;

    @Column(name = "recorded_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime recordedAt = OffsetDateTime.now();
}
