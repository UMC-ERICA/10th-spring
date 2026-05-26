package umc.server.global.apiPayload.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.GeneralErrorCode;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;
    public ProjectException(String message) {
        super(message); //직접 생성자 만들었다, 그럼 Lombok이 자동생성자 안해줌
        this.errorCode = GeneralErrorCode.BAD_REQUEST;
    }
}
