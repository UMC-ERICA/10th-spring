package umc.server.domain.store.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "STORE201_1", "성공적으로 가게 미션을 생성했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
