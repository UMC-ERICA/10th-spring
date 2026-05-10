package umc.server.domain.mission.dto;


import java.time.LocalDate;
import umc.server.domain.mission.enums.Status;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            Status status
    ) {
    }
}
