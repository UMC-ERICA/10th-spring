package umc.server.domain.member.dto;

import java.time.LocalDateTime;

public class MemberResponseDTO {
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {
    }

    public record MyPageDTO(
            String username,
            String email,
            Integer currentPoint
    ) {
    }

    public record HomeDTO(
            String regionName,
            Integer missionProgress,
            umc.server.domain.mission.dto.MissionResponseDTO.MissionListDTO availableMissions
    ) {
    }
}
