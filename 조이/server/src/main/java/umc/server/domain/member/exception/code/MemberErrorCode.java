package umc.server.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "회원이 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "비밀번호가 올바르지 않습니다.");
    private final HttpStatus status;
    private final String message;
    private final String code;
}
