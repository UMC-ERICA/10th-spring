package umc.server.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess") // 필드 이름
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) { // 메서드 이름
        return new ApiResponse<>(true, code.getCode(),code.getMessage(), result);
    }
    //실패
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(),code.getMessage(),result);
    }
}