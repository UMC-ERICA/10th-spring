package umc.server.domain.store.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * 미션 요청 DTO 모음
 */
public class MissionReqDTO {

    /**
     * 미션 생성 요청 DTO
     *
     * @Future: 오늘 이후 날짜만 허용 (마감일이 과거면 의미 없으므로)
     */
    public record CreateMission(

            @NotNull(message = "마감일은 필수입니다.")
            @Future(message = "마감일은 오늘 이후 날짜여야 합니다.")
            LocalDate deadline,

            @NotNull(message = "포인트는 필수입니다.")
            @Min(value = 1, message = "포인트는 1 이상이어야 합니다.")
            Integer point,

            @NotBlank(message = "미션 내용은 필수입니다.")
            String conditional
    ) {
    }
}
