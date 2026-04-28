package umc.server.global.exception.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.server.global.exception.code.BaseCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "data"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("data")
    private final T data;


    public static <T> ApiResponse<T> success(BaseCode baseCode, T data) {
        return new ApiResponse<>(true, baseCode.getCode(), baseCode.getMessage(), data);
    }

    public static <T> ApiResponse<T> faliure(BaseCode baseCode, T data) {
        return new ApiResponse<>(true, baseCode.getCode(), baseCode.getMessage(), data);
    }
}
