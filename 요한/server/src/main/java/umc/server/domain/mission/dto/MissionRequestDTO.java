package umc.server.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.mission.enums.MissionStatus;

public class MissionRequestDTO {
    public record MissionListRequestDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            @NotNull(message = "미션 상태는 필수입니다.")
            MissionStatus status,
            @NotNull(message = "페이지 번호는 필수입니다.")
            @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
            Integer page,
            @NotNull(message = "페이지 크기는 필수입니다.")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize
    ) {
    }
}
