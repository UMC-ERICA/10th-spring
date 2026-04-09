package umc.server.global.payload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    ErrorReasonDTO getReason();
    ErrorReasonDTO getReasonHttpStatus();
}
