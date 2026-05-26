package umc.server.domain.member.exception;

import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
public class MemberException extends RuntimeException {

    private final BaseErrorCode code;

    public MemberException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
