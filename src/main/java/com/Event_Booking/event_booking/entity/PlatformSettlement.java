package com.Event_Booking.event_booking.entity;

import com.Event_Booking.event_booking.enums.SettlementStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "platform_settlements", indexes = {
        @Index(name = "idx_settlements_restaurant", columnList = "restaurant_id"),
        @Index(name = "idx_settlements_status",     columnList = "status")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PlatformSettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // Đầu tuần
    @Column(name = "period_start", nullable = false)
    private OffsetDateTime periodStart;

    // Cuối tuần
    @Column(name = "period_end", nullable = false)
    private OffsetDateTime periodEnd;

    // Tổng doanh thu trong kỳ
    @Column(name = "total_revenue", nullable = false, precision = 14, scale = 2)
    private BigDecimal totalRevenue;

    // Lưu lại % lúc tạo — tránh bị ảnh hưởng khi admin đổi % sau
    @Column(name = "settlement_rate", nullable = false, precision = 5, scale = 4)
    @Builder.Default
    private BigDecimal settlementRate = new BigDecimal("0.2000"); // 20%

    // total_revenue × settlement_rate
    @Column(name = "settlement_amount", nullable = false, precision = 14, scale = 2)
    private BigDecimal settlementAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private SettlementStatus status = SettlementStatus.PENDING;

    @Column(name = "due_date", nullable = false)
    private OffsetDateTime dueDate;

    @Column(name = "paid_at")
    private OffsetDateTime paidAt;

    // Các giao dịch PayOS liên quan đến kỳ quyết toán này
    @OneToMany(mappedBy = "settlement", cascade = CascadeType.ALL)
    @Builder.Default
    private List<PaymentTransaction> transactions = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
