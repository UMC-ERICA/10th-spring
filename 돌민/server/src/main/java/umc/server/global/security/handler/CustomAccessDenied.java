package umc.server.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.GeneralErrorCode;

import java.io.IOException;

/**
 * 인증은 됐지만 권한이 없는 경우 처리 (403 Forbidden)
 *
 * 예시: 일반 유저가 관리자 전용 API 호출
 */
public class CustomAccessDenied implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        BaseErrorCode code = GeneralErrorCode.FORBIDDEN;

        // 응답 Content-Type, HTTP 상태코드 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        // 통일된 ApiResponse 형태로 응답
        ApiResponse<Void> errorResponse = ApiResponse.onFailure(code, null);

        objectMapper.writeValue(response.getOutputStream(), errorResponse);
    }
}
