package umc.server.domain.mission.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.ProjectException;

public class MissionnException extends ProjectException {
    public MissionnException(BaseErrorCode code) {
        super(code);
    }
}