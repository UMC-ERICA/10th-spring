package umc.server.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 생성되었습니다."),
    OK(HttpStatus.OK, "REVIEW200_1", "리뷰가 성공적으로 조회되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
