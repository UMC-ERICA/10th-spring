package umc.server.domain.mission.dto.response;

public record MemberMissionResponse(
        Long memberMissionId,
        Long missionId,
        String title,
        String status,
        Integer rewardPoint
) {
}