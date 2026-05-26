package umc.server.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import umc.server.domain.mission.enums.MissionStatus;

import java.time.LocalDate;

public class MissionReqDTO {

    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotNull(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ){}
    public record GetMission(
            MissionStatus status
    ) {}
}
