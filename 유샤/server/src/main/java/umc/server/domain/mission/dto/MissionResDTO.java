package umc.server.domain.mission.dto;


import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetProgress(
            Integer progress
    ){}

    @Builder
    public record GetMissionList(
            List<MissionCard> missionList
    ){}

    @Builder
    public record MissionCard(
            Long missionId,
            String conditional,
            Integer point
    ){}

    @Builder
    public record Complete(
            Long missionId
    ){}
}
