package umc.server.domain.mission.dto.response;

public record MissionResponse(
        Long missionId,
        String title,
        String content,
        Integer rewardPoint,
        String status
) {
}