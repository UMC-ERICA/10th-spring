package umc.server.domain.mission.converter;

import umc.server.domain.mission.entity.Mission;
import umc.server.domain.store.dto.request.MissionReqDTO;
import umc.server.domain.store.entity.Store;

public class MissionConverter {

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ) {
        return Mission.builder()
                .store(store)
                .missionContent(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline().atStartOfDay())
                .build();
    }
}
