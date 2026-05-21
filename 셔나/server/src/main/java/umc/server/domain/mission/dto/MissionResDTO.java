package umc.server.domain.mission.dto;

import lombok.Builder;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.store.enums.StoreCategory;

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
            List<MissionDTO> missionList,

            Integer pageSize,    // 한 페이지 크기
            Integer totalPage,   // 전체 페이지 수
            Long totalElements,  // 전체 데이터 개수
            Boolean isFirst,     // 첫 번째 페이지 여부
            Boolean isLast       // 마지막 페이지 여부
    ) {}

    @Builder
    public record UpdateMissionStatusResultDTO(
            Long missionId,
            MissionStatus missionStatus
    ) {}

    @Builder
    public record HomeMissionDTO(
            Long missionId,
            String storeName,
            StoreCategory category,
            String missionTitle,
            Integer rewardPoints,
            LocalDateTime deadline
    ) {}

    @Builder
    public record GetHomeMissionListDTO(
            List<HomeMissionDTO> homeMissionList
    ) {}
}
