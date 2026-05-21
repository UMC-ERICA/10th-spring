package umc.server.domain.mission.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;

/**
 * 미션 도메인 전용 예외 클래스
 */
public class MissionException extends RuntimeException {

    private final BaseErrorCode code;

    public MissionException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BaseErrorCode getCode() {
        return code;
    }
}
