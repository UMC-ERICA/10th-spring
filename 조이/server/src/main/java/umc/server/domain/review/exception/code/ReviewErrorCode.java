package umc.server.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {
    QUERY_NOT_FOUND(HttpStatus.NOT_FOUND, "REVIEW404_1", "리뷰를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


}
