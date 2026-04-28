package umc.server.domain.notification.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.notification.dto.NotificationReadStatusResponse;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    public NotificationReadStatusResponse existUnreadNotifications(Long memberId) {
        // TODO : 추후 구현 - memberId로 알림 조회 후 읽지 않은 알림이 있는지 확인
        boolean existsUnread = true; // 임시로 true 반환
        return new NotificationReadStatusResponse(existsUnread);
    }
}
