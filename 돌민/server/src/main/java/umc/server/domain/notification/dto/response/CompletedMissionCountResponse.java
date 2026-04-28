package umc.server.domain.notification.dto.response;

public record CompletedMissionCountResponse(
        Long memberId,
        Long completedMissionCount
) {
}