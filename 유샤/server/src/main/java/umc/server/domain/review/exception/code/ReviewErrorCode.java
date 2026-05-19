package umc.server.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "쿼리문이 유효하지 않습니다."),;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
