package umc.server.domain.mission.converter;

import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.GetMissionList toGetMissionListResult(
            List<Mission> missionList
    ){
        List<MissionResDTO.MissionCard> missionCardList = missionList.stream()
                .map(mission -> MissionResDTO.MissionCard.builder()
                        .missionId(mission.getId())
                        .conditional(mission.getConditional())
                        .point(mission.getPoint())
                        .build())
                .toList();

        return MissionResDTO.GetMissionList.builder()
                .missionList(missionCardList)
                .build();
    }

    public static MissionResDTO.GetProgress toGetProgressResult(
            Integer progress
    ){
        return MissionResDTO.GetProgress.builder()
                .progress(progress)
                .build();
    }

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    // 워크북
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
