package umc.server.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.BaseCode;
import umc.server.global.exception.code.CommonErrorCode;
import umc.server.global.security.service.CustomUserDetailsService;
import umc.server.global.security.util.JwtUtil;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 토큰 가져오기
            String token = request.getHeader("Authorization");
            // token이 없거나 Bearer가 아니면 넘기기
            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            // Bearer이면 추출
            token = token.replace("Bearer ", "");
            // AccessToken 검증하기: 올바른 토큰이면
            if (jwtUtil.isValid(token)) {
                // 토큰에서 이메일 추출
                String email = jwtUtil.getEmail(token);
                // 인증 객체 생성: 이메일로 찾아온 뒤, 인증 객체 생성
                UserDetails user = customUserDetailsService.loadUserByUsername(email);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                // 인증 완료 후 SecurityContextHolder에 넣기
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            ObjectMapper mapper = new ObjectMapper();
            BaseCode code = CommonErrorCode.UNAUTHORIZED;

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(code.getHttpStatus().value());

            ApiResponse<Void> errorResponse = ApiResponse.failure(code,null);

            mapper.writeValue(response.getOutputStream(), errorResponse);
        }

    }
}
