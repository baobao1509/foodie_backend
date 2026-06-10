package com.Event_Booking.event_booking.entity;

import com.Event_Booking.event_booking.enums.DocumentStatus;
import com.Event_Booking.event_booking.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipper_documents", indexes = {
        @Index(name = "idx_shipper_docs_shipper", columnList = "shipper_id, status")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ShipperDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shipper_id", nullable = false)
    private User shipper;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;
    // ID_CARD | DRIVER_LICENSE | VEHICLE_REGISTRATION

    // URL file ảnh lưu trên S3/MinIO
    @Column(name = "file_url", nullable = false, length = 500)
    private String fileUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private DocumentStatus status = DocumentStatus.PENDING;
    // PENDING | APPROVED | REJECTED

    // Admin nào đã review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by")
    private User reviewedBy;

    @Column(name = "reviewed_at")
    private OffsetDateTime reviewedAt;

    // Lý do từ chối nếu REJECTED
    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdAt = OffsetDateTime.now();
}