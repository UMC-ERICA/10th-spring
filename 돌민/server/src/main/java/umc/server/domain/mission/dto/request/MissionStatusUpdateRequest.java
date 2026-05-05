package umc.server.domain.mission.dto.request;

import umc.server.domain.mission.enums.MissionStatus;

public record MissionStatusUpdateRequest(
        MissionStatus status
) {
}