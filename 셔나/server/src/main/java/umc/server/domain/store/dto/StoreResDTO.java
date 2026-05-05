package umc.server.domain.store.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {

    @Builder
    public record CreateMissionResultDTO(
            Long missionId,
            LocalDateTime createdAt
    ) {}
}
