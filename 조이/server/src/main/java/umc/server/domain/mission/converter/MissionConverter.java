package umc.server.domain.mission.converter;

import java.util.List;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.Status;
import umc.server.domain.store.entity.Store;

public class MissionConverter {
    //미션가져오기
    public static MissionResDTO.MissionsResDTO toMissionsResDTO(Mission mission, Status status) {
        return MissionResDTO.MissionsResDTO.builder()
                .missionId(mission.getId())
                .missionInfo(mission.getMissionInfo())
                .point(mission.getPoint())
                .status(status)
                .build();
    }

    // 가게 미션 생성
    public static Mission toMission(Store store, MissionReqDTO.CreateMission dto) {
        return Mission.builder()
                .store(store)
                .point(dto.point())
                .missionInfo(dto.missionInfo())
                .deadline(dto.deadline())
                .build();
    }

    //페이지네이션 툴 생성
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer PageNumber,
            Integer PageSize
    ) {
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(PageNumber)
                .pageSize(PageSize)
                .build();
    }
}
