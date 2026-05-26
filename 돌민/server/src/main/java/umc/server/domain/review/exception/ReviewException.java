package umc.server.domain.review.exception;

import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
public class ReviewException extends RuntimeException {

    private final BaseErrorCode code;

    public ReviewException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
