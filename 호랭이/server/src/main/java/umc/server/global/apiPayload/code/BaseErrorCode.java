package umc.server.global.apiPayload.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {

    HttpStatus getStatus(); //HTTP 상태코드 반환
    String getCode();
    String getMessage();

}
