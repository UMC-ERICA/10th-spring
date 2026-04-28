package umc.server.domain.mission.dto;

public class MissionReqDTO {
    public record GetMission(
            Long memberId
    ) {}
}
