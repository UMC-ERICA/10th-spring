package umc.server.domain.store.exception;

import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
public class StoreException extends RuntimeException {

    private final BaseErrorCode code;

    public StoreException(BaseErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }

}
