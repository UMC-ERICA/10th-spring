package umc.server.domain.store.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


}
