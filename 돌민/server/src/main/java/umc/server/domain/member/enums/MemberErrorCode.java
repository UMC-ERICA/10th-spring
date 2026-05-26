package umc.server.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 회원이 존재하지 않습니다."),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_2", "회원 주소가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
