package umc.server.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    public record GetProgress(
       Long addressId
    ){}

    public record CreateMission(
            @NotNull(message="마감기한은 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "미션성공포인트는 필수입니다.")
            Integer point,
            @NotNull(message="조건은 빈칸일 수 없습니다.")
            String conditional
    ){}
}
