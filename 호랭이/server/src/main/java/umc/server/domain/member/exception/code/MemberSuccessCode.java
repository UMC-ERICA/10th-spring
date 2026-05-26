package umc.server.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "MEMBER200", "성공입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
