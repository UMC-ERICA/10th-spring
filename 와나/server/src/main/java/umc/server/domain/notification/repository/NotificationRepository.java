package umc.server.domain.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    boolean existsByMemberIdAndIsRead(Long memberId, boolean isRead);
}
