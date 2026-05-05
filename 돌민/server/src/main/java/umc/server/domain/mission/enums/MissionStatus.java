package umc.server.domain.mission.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionStatus {

    READY("대기중"),
    IN_PROGRESS("진행중"),
    COMPLETE("완료");

    private final String description;
}