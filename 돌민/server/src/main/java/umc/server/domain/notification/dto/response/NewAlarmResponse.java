package umc.server.domain.notification.dto.response;

public record NewAlarmResponse(
        Boolean hasNewAlarm,
        Long unreadCount
) {
}