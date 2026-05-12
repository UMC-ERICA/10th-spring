package umc.server.domain.mission.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

public class MissionnException extends GeneralException {
    public MissionnException(BaseErrorCode code) {
        super(code);
    }
}