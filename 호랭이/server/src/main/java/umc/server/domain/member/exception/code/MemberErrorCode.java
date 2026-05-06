package umc.server.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode{

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1", // 요청한 리소스를 찾을 수 없음
            "해당 사용자를 찾을 수 없습니다."),
    ;

    private final HttpStatus status; // HTTP 상태코드
    private final String code; // 에러 식별 코드
    private final String message; // 사용자에게 보여줄 메시지
    }

