package umc.server.domain.mission.exception;

import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
public class MissionException extends RuntimeException {

    private final BaseErrorCode code;

    public MissionException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

}
