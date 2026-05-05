package umc.server.domain.store.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class StoreReqDTO {

    public record CreateMissionDTO(
            @Schema(description = "미션 제목", example = "매장에서 10,000원 이상 결제")
            String missionTitle,

            @Schema(description = "미션 설명", example = "음식 메뉴 무관하게 매장에서 10,000원 이상 결제 시 포인트가 적립됩니다.")
            String missionDescription,

            @Schema(description = "미션 마감일", example = "2026-05-31T23:59:59")
            LocalDateTime deadline,

            @Schema(description = "적립 포인트", example = "10")
            Integer rewardPoints,

            @Schema(description = "미션 활성여부", example = "true")
            boolean isActive
    ) {}
}
