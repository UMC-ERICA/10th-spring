package umc.server.domain.mission.dto;

import umc.server.domain.mission.entity.Mission;

import java.util.List;

public class MissionResDTO {

    public record getProgress(
            Integer progress
    ){}

    public record getMissionList(
            List<MissionCard> missionList
    ){}

    public record MissionCard(
            Long missionId,
            String title
    ){}
}
