package umc.server.global.apiPayload.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode baseErrorCode;
}
