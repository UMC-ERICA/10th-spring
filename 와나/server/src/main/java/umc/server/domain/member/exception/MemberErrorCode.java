package umc.server.domain.member.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.exception.code.BaseCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER_4001", "회원을 찾을 수 없습니다."),


    MEMBER_ADDR_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMADDR_4001", "회원의 주소를 찾을 수 없습니다.")

    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
