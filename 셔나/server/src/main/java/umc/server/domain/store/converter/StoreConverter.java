package umc.server.domain.store.converter;

import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.dto.StoreResDTO;

public class StoreConverter {

    // DTO -> entity
    public static Mission toMission(StoreReqDTO.CreateMissionDTO request) {
        return Mission.builder()
                .missionTitle(request.missionTitle())
                .missionDescription(request.missionDescription())
                .deadline(request.deadline())
                .rewardPoints(request.rewardPoints())
                .isActive(request.isActive())
                .build();
    }

    // entity -> 가게 미션 생성 DTO
    public static StoreResDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return StoreResDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(mission.getCreatedAt())
                .build();

    }
}
