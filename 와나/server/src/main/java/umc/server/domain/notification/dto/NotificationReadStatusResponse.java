package umc.server.domain.notification.dto;

public record NotificationReadStatusResponse(
        boolean existsUnread
) {
}
