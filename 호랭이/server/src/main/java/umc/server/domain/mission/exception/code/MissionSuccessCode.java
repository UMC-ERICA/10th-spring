package umc.server.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED,//created 상태코드객체
            "MISSION200_1",
            "성공적으로 미션을 생성했습니다."),
    OK(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 미션을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
