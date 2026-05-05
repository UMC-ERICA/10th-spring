package umc.server.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록을 성공적으로 조회했습니다."),
    MISSION_STATUS_UPDATED(HttpStatus.OK, "MISSION200_2", "미션 상태가 성공적으로 업데이트되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
