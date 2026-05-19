package umc.server.domain.mission.dto;

import java.util.List;
import lombok.Builder;
import umc.server.domain.mission.enums.Status;

public class MissionResDTO {
    @Builder
    public record MissionsResDTO(
            Long missionId,
            String missionInfo,
            Integer point,
            Status status
    ) {
    }

    @Builder
    public record MissionsGetResDTO(
            Long memberId,
            Status status,
            List<MissionsResDTO> missions
    ) {
    }

    // 페이지네이션 DTO
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
    }

}
