package umc.server.domain.review.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

/**
 * 리뷰 도메인에서 발생하는 에러 코드 모음
 */
@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
