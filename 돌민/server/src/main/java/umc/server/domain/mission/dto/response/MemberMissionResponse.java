package umc.server.domain.mission.dto.response;

import umc.server.domain.mission.enums.MemberMissionStatus;

import java.time.LocalDateTime;

public record MemberMissionResponse(
        Long memberMissionId,
        Long missionId,
        String storeName,
        String missionContent,
        Integer point,
        String status,
        LocalDateTime acceptedAt,
        LocalDateTime completedAt,
        LocalDateTime deadline
) {
    public MemberMissionResponse(
            Long memberMissionId,
            Long missionId,
            String storeName,
            String missionContent,
            Integer point,
            MemberMissionStatus status,
            LocalDateTime acceptedAt,
            LocalDateTime completedAt,
            LocalDateTime deadline
    ) {
        this(
                memberMissionId,
                missionId,
                storeName,
                missionContent,
                point,
                status.name(),
                acceptedAt,
                completedAt,
                deadline
        );
    }
}
