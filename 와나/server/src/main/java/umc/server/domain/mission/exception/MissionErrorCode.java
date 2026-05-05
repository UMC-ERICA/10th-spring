package umc.server.domain.mission.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.exception.code.BaseCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseCode {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION_4001", "미션을 찾을 수 없습니다."),

    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
