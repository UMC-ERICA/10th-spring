package umc.server.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰를 조회했습니다."),
    CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰가 성공적으로 작성되었습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
