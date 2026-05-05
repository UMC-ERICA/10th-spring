package umc.server.domain.mission.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionDTO(
            Long missionId,
            String missionTitle,
            String missionDescription,
            int rewardPoints,
            MissionStatus missionStatus,
            LocalDateTime deadline
    ) {}

    @Builder
    public record GetMissionListDTO(
            List<MissionDTO> missionList
    ) {}

    @Builder
    public record UpdateMissionStatusResultDTO(
            Long missionId,
            MissionStatus missionStatus
    ) {}
}
