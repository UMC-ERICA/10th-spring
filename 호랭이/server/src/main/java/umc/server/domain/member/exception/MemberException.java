package umc.server.domain.member.exception;

import lombok.Getter;
import umc.server.domain.member.exception.code.MemberErrorCode;

@Getter
public class MemberException extends RuntimeException {
    private final MemberErrorCode errorCode;

    public MemberException(MemberErrorCode errorCode){ //정해진 에러객체 사용
        super(errorCode.getMessage());
        this.errorCode = errorCode; //객체의 errorcode 필드에 매개변수 errorcode 값을 넣어라
    }
}
