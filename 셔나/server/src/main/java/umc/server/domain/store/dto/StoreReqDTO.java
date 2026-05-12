package umc.server.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class StoreReqDTO {

    public record CreateMissionDTO(
            @NotNull(message = "미션 제목은 필수입니다.")
            @Schema(description = "미션 제목", example = "매장에서 10,000원 이상 결제")
            String missionTitle,

            @NotNull(message = "미션 설명은 필수입니다.")
            @Schema(description = "미션 설명", example = "음식 메뉴 무관하게 매장에서 10,000원 이상 결제 시 포인트가 적립됩니다.")
            String missionDescription,

            @NotNull(message = "미션 마감일은 필수입니다.")
            @Schema(description = "미션 마감일", example = "2026-05-31T23:59:59")
            LocalDateTime deadline,

            @NotNull(message = "적립 포인트는 필수입니다.")
            @Schema(description = "적립 포인트", example = "10")
            Integer rewardPoints,

            @NotNull(message = "미션 활성여부는 필수입니다.")
            @Schema(description = "미션 활성여부", example = "true")
            boolean isActive
    ) {}
}
