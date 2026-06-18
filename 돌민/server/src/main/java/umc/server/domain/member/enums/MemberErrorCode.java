package umc.server.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "해당 회원이 존재하지 않습니다."),

    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_2",
            "회원 주소가 존재하지 않습니다."),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,
            "MEMBER401_1",
            "비밀번호가 일치하지 않습니다."),

    TERM_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_3",
            "해당 약관이 존재하지 않습니다."),

    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "지원하지 않는 소셜 로그인 제공자입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
