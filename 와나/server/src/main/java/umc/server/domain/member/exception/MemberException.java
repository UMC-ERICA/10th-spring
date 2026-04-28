package umc.server.domain.member.exception;

import umc.server.global.exception.GlobalException;
import umc.server.global.exception.code.BaseCode;

public class MemberException extends GlobalException {
    public MemberException(BaseCode baseCode) {
        super(baseCode);
    }
}
