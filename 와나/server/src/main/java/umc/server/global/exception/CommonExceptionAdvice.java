package umc.server.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.BaseCode;
import umc.server.global.exception.code.CommonErrorCode;

@RestControllerAdvice
public class CommonExceptionAdvice {
    // 프로젝트에서 발생한 예외 처리
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiResponse<Void>> handleMemberException(
            GlobalException e
    ) {
        BaseCode errorCode = e.getBaseCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ApiResponse.failure(errorCode, null));
    }

    // 그 외의 정의되지 않은 모든 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {

        BaseCode code = CommonErrorCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(code.getHttpStatus())
                .body(ApiResponse.failure(
                                code,
                                ex.getMessage()
                        )
                );
    }
}
