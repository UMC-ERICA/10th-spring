package umc.server.domain.mission.converter;

import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;

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
}
