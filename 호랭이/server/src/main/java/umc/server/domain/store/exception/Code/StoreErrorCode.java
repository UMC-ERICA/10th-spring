package umc.server.domain.store.exception.Code;

import jdk.jfr.Registered;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum StoreErrorCode implements BaseErrorCode {

    STORE_NOTE_FOUND(HttpStatus.NOT_FOUND,
            "STORE404_1",
            "해당 가게를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
