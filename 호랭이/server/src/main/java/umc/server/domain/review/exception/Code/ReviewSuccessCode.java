package umc.server.domain.review.exception.Code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode {

    CREATED(HttpStatus.CREATED,
            "MISSION200_1",
            "성공적으로 리뷰를 생성했습니다."),
    OK(HttpStatus.OK,
            "MISSION200_2",
            "성공적으로 리뷰를 조회했습니다."),


    private final HttpStatus status;
    private final String code;
    private final String message;
}
