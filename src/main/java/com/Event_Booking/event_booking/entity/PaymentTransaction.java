package com.Event_Booking.event_booking.entity;

import com.Event_Booking.event_booking.enums.TransactionStatus;
import com.Event_Booking.event_booking.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "payment_transactions", indexes = {
        @Index(name = "idx_payment_tx_order",      columnList = "order_id"),
        @Index(name = "idx_payment_tx_settlement", columnList = "settlement_id"),
        @Index(name = "idx_payment_tx_payos",      columnList = "payos_order_id")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    // NULL nếu là settlement transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // NULL nếu là order transaction
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id")
    private PlatformSettlement settlement;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;
    // ORDER_PAYMENT | SETTLEMENT | SHIPPER_TRANSFER

    // ID giao dịch từ PayOS
    @Column(name = "payos_order_id", length = 255)
    private String payosOrderId;

    // Data QR code từ PayOS
    @Column(name = "payos_qr_code", columnDefinition = "TEXT")
    private String payosQrCode;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TransactionStatus status = TransactionStatus.PENDING;

    // Ai trả tiền
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    private User payer;

    // Ai nhận tiền
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee_id")
    private User payee;

    // Raw webhook data từ PayOS để audit/debug
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "webhook_data", columnDefinition = "jsonb")
    private Map<String, Object> webhookData;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
