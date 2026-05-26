package umc.server.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST,
            "COMMON400",
            "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,
            "COMMON401",
            "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN,
            "COMMON403",
            "접근이 금지되었습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,
            "COMMON404",
            "요청하신 대상을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "COMMON500",
            "서버 오류가 발생했습니다."),

    // Member 관련 에러
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404",
            "존재하지 않는 사용자입니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,
            "MEMBER400_1",
            "이미 가입된 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,
            "MEMBER400_2",
            "비밀번호가 일치하지 않습니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER_MISSION404",
            "해당 미션 수행 기록이 존재하지 않습니다."),
    MEMBER_MISSION_NOT_COMPLETE(HttpStatus.BAD_REQUEST,
            "MEMBER_MISSION400",
            "완료한 미션에 대해서만 리뷰를 작성할 수 있습니다."),

    // Store 관련 에러
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,
            "STORE404",
            "해당 가게가 존재하지 않습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
