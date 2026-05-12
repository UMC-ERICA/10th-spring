package umc.server.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200_1", "성공적으로 요청을 처리했습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201_1", "리소스가 성공적으로 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
