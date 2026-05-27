package umc.server.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "유저를 찾을 수 없습니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "이미 존재하는 아이디입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
