package umc.server.domain.mission.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.mission.enums.MemberMissionStatus;

/**
 * "내가 진행중인 미션 조회" 요청 DTO
 */
public record MyMissionRequest(

        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "미션 상태는 필수입니다.")
        MemberMissionStatus status,

        @NotNull(message = "페이지 번호는 필수입니다.")
        @Min(value = 0, message = "페이지 번호는 0 이상이어야 합니다.")
        Integer page,

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        @Max(value = 50, message = "페이지 크기는 50 이하여야 합니다.")
        Integer size
) {
}
