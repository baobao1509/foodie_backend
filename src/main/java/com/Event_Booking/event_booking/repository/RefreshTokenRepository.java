package com.Event_Booking.event_booking.repository;
import com.Event_Booking.event_booking.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.UUID;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByTokenHashAndRevokedFalse(String tokenHash);

    // Revoke tất cả token của 1 user (logout all devices)
    @Modifying
    @Query("UPDATE RefreshToken t SET t.revoked = true WHERE t.user.id = :userId")
    void revokeAllByUserId(UUID userId);

    // Revoke 1 token cụ thể (logout device hiện tại)
    @Modifying
    @Query("UPDATE RefreshToken t SET t.revoked = true WHERE t.tokenHash = :tokenHash")
    void revokeByTokenHash(String tokenHash);

    // Xoá token hết hạn — chạy bằng @Scheduled cleanup job
    @Modifying
    @Query("DELETE FROM RefreshToken t WHERE t.expiresAt < CURRENT_TIMESTAMP OR t.revoked = true")
    void deleteExpiredAndRevoked();
}
