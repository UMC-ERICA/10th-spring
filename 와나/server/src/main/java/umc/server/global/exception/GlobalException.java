package umc.server.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.server.global.exception.code.BaseCode;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException{
    private final BaseCode baseCode;
}
