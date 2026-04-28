package umc.server.global.apiPayload.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;
    public ProjectException(String message) {
        super(message);
    }
}
