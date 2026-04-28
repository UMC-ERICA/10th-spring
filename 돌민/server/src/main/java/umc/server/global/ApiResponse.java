package umc.server.global;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 - 기본 성공 코드 + result 있음
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(
                true,
                GeneralSuccessCode.OK.getCode(),
                GeneralSuccessCode.OK.getMessage(),
                result
        );
    }

    // 성공한 경우 - 기본 성공 코드 + result 없음
    public static ApiResponse<Void> onSuccess() {
        return new ApiResponse<>(
                true,
                GeneralSuccessCode.OK.getCode(),
                GeneralSuccessCode.OK.getMessage(),
                null
        );
    }

    // 성공한 경우 - 원하는 성공 코드 지정 + result 있음
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    // 성공한 경우 - 원하는 성공 코드 지정 + result 없음
    public static ApiResponse<Void> onSuccess(BaseSuccessCode code) {
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                null
        );
    }

    // 실패한 경우
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,
                code.getCode(),
                code.getMessage(),
                result
        );
    }
}