package umc.server.domain.store.exception;

import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code){ super(code);}
}
