package umc.server.domain.member.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode code) {
        super(code);
    }
}
