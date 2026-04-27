package umc.server.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 회원을 조회했습니다."),
    CREATED(HttpStatus.CREATED, "MEMBER201_1", "성공적으로 회원을 생성했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
