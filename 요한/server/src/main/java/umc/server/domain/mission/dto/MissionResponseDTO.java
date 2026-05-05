package umc.server.domain.mission.dto;

import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResponseDTO {

    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            String content,
            Integer rewardValue,
            MissionStatus status,
            LocalDateTime createdAt
    ) {
    }

    public record MissionListDTO(
            List<MissionPreviewDTO> missionList,
            Integer listSize,
            Integer totalPage,
            Long totalElements,
            Boolean isFirst,
            Boolean isLast
    ) {
    }

    public record MissionCompleteResultDTO(
            Long userMissionId,
            MissionStatus status,
            LocalDateTime updatedAt
    ) {
    }
}
