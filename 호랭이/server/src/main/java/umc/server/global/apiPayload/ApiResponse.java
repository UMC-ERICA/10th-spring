package umc.server.global.apiPayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.server.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess","code","message","result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    //실패
    private static <T> ApiResponse<T> onFailure(BaseErrorCode, T result){
        result new ApiResponse<>(false, code.getCode(), code.getMessage(),result);
    }
}
