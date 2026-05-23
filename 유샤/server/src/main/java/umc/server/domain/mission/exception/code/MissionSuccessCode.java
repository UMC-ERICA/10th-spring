package umc.server.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MISSION200_1",
            "성공적으로 미션을 조회했습니다."),
    PROGRESS_FOUND(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 지역미션진척도를 조회했습니다."),
    MISSION_COMPLETE(HttpStatus.OK,
            "MISSION200_3",
            "미션 complete: false -> true"),
    CREATED(HttpStatus.CREATED,
            "MISSION201_1",
            "성공적으로 미션을 생성했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
