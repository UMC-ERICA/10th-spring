package umc.server.domain.mission.dto.request;

import jakarta.validation.constraints.NotNull;
import umc.server.domain.mission.enums.MemberMissionStatus;

public record MemberMissionSearchRequest(
        @NotNull MemberMissionStatus status
) {
}
