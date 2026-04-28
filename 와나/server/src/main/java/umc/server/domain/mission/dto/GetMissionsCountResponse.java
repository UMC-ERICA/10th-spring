package umc.server.domain.mission.dto;

public record GetMissionsCountResponse(
        int totalCount,
        int completedCount
) {
}
