package umc.server.domain.member.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;

public class MemberException extends RuntimeException {
    public MemberException(BaseErrorCode code) {
        super(code.getMessage());
    }
}
