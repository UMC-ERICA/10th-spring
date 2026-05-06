package umc.server.global.apiPayload.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException { //컴파일 문제없는데 실행하다 터지는 에러
    private final BaseErrorCode errorCode;
}
