package umc.server.domain.review.exception;

import umc.server.domain.review.exception.code.ReviewErrorCode;
import umc.server.global.apiPayload.code.BaseErrorCode;

public class ReviewException extends RuntimeException {
    public ReviewException(BaseErrorCode message) {
        super(message.getMessage());
    }
}
