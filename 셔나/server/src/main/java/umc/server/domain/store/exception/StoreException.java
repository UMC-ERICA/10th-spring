package umc.server.domain.store.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.GeneralException;

public class StoreException extends GeneralException {
    public StoreException(BaseErrorCode code) {
        super(code);
    }
}
