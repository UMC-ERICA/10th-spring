package umc.server.domain.mission.dto;


import java.util.List;

public class MissionResDTO {

    public record GetProgress(
            Integer progress
    ){}

    public record GetMissionList(
            List<MissionCard> missionList
    ){}

    public record MissionCard(
            Long missionId,
            String title
    ){}

    public record Complete(
            Long missionId
    ){}
}
