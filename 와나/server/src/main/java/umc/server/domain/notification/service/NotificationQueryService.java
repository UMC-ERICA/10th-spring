package umc.server.domain.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.notification.dto.NotificationReadStatusResponse;
import umc.server.domain.notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationReadStatusResponse existUnreadNotifications(Long memberId) {
        boolean existsUnread = notificationRepository.existsByMemberIdAndIsRead(memberId, false);
        return new NotificationReadStatusResponse(existsUnread);
    }
}
