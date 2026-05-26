package umc.server.domain.store.dto.response;

import lombok.Builder;

public class MissionResDTO {
    @Builder
    public record GetMission(
        Long missionId,
        Integer point,
        String conditional
    ) {
    }
}