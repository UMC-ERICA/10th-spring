package umc.server.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.review.exception.ReviewException;
import umc.server.domain.store.exception.StoreException;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralErrorCode;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 회원 관련 예외 처리
     */
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponse<?>> handleMemberException(MemberException e) {
        return ResponseEntity
                .status(e.getCode().getStatus())
                .body(ApiResponse.onFailure(e.getCode(), null));
    }

    /**
     * 미션 관련 예외 처리
     */
    @ExceptionHandler(MissionException.class)
    public ResponseEntity<ApiResponse<?>> handleMissionException(MissionException e) {
        return ResponseEntity
                .status(e.getCode().getStatus())
                .body(ApiResponse.onFailure(e.getCode(), null));
    }

    /**
     * 가게 관련 예외 처리
     */
    @ExceptionHandler(StoreException.class)
    public ResponseEntity<ApiResponse<?>> handleStoreException(StoreException e) {
        return ResponseEntity
                .status(e.getCode().getStatus())
                .body(ApiResponse.onFailure(e.getCode(), null));
    }

    /**
     * 리뷰 관련 예외 처리
     */
    @ExceptionHandler(ReviewException.class)
    public ResponseEntity<ApiResponse<?>> handleReviewException(ReviewException e) {
        return ResponseEntity
                .status(e.getCode().getStatus())
                .body(ApiResponse.onFailure(e.getCode(), null));
    }

    /**
     * @Valid 검증 실패 예외 처리,,,,, 아직 이해하지 못함
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ResponseEntity
                .status(GeneralErrorCode.BAD_REQUEST.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.BAD_REQUEST, errorMessage));
    }

    /**
     * 그 외 모든 예외 처리 (최후의 보루)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        return ResponseEntity
                .status(GeneralErrorCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(ApiResponse.onFailure(GeneralErrorCode.INTERNAL_SERVER_ERROR, null));
    }
}
