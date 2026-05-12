package umc.server.domain.mission.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import umc.server.domain.mission.enums.Status;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            @NotNull(message = "마감 날짜는 필수입니다.")
            LocalDate deadline,
            @NotNull(message = "포인트는 필수입니다.")
            Integer point,
            @NotNull(message = "미션 상태는 빈칸일수 없습니다.")
            Status status,
            @NotBlank(message = "미션 정보는 빈칸일수 없습니다.")
            String missionInfo
    ) {
    }
}
