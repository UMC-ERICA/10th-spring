package umc.server.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements BaseCode {
    OK(HttpStatus.OK, "COMMON200", "요청이 성공적으로 처리되었습니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "리소스가 성공적으로 생성되었습니다."),
    ACCEPTED(HttpStatus.ACCEPTED, "COMMON202", "요청이 수락되었습니다. 처리가 완료되지 않았습니다."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "COMMON204", "요청이 성공적으로 처리되었지만 반환할 콘텐츠가 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
