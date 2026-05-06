package umc.server.domain.mission.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionStatus {

    READY("READY"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETE("COMPLETE");

    private final String description;
}
