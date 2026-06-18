package umc.server.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MEMBER200_1", "멤버가 성공적으로 조회되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_2", "로그인에 성공하였습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
