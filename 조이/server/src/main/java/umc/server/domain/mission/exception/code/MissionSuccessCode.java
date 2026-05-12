package umc.server.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED, "MISSION201_1", "미션이 성공적으로 생성되었습니다."),
    OK(HttpStatus.OK, "MISSION200_1", "미션이 성공적으로 조회되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
