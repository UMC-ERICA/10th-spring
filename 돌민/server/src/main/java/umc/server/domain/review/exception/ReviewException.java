package umc.server.domain.review.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;

/**
 * 리뷰 도메인 전용 예외 클래스
 */
public class ReviewException extends RuntimeException {

    private final BaseErrorCode code;

    public ReviewException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BaseErrorCode getCode() {
        return code;
    }
}
