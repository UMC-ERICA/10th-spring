package umc.server.domain.mission.converter;

import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MissionConverter {
    public static MissionResDTO.MissionDetail toMissionDetail(Mission mission) {
        return MissionResDTO.MissionDetail.builder()
                .storeName(mission.getStore().getName())
                .category(mission.getStore().getCategory().name())
                .content(mission.getConditional())
                .dDay((int) ChronoUnit.DAYS.between(LocalDate.now(), mission.getDDay()))
                .status(mission.getStatus())
                .build();
    }
}
