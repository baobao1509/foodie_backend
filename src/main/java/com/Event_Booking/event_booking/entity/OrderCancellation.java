package com.Event_Booking.event_booking.entity;

import com.Event_Booking.event_booking.enums.CancelReasonCode;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_cancellations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderCancellation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false, unique = true)
    private Order order;

    // Ai hủy: customer, restaurant owner, hay system (timeout)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancelled_by")
    private User cancelledBy; // NULL nếu system tự hủy

    @Enumerated(EnumType.STRING)
    @Column(name = "reason_code", nullable = false)
    private CancelReasonCode reasonCode;
    // CUSTOMER_REQUEST | RESTAURANT_REJECTED | NO_SHIPPER | TIMEOUT | OTHER

    // Ghi chú thêm
    @Column(name = "reason_note", columnDefinition = "TEXT")
    private String reasonNote;

    // Có cần hoàn tiền không (true khi đã thanh toán online trước)
    @Column(name = "refund_required", nullable = false)
    @Builder.Default
    private Boolean refundRequired = false;

    @Column(name = "refunded_at")
    private OffsetDateTime refundedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();
}