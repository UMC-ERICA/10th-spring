package umc.server.domain.notification.dto.response;

public record NewAlarmResponse(
        boolean hasNewAlarm,
        long unreadCount
) {
}