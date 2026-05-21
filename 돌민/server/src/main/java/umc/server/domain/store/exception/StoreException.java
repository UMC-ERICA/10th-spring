package umc.server.domain.store.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;

public class StoreException extends RuntimeException {

    private final BaseErrorCode code;

    public StoreException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public BaseErrorCode getCode() {
        return code;
    }
}
