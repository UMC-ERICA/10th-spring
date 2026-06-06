package umc.server.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.server.domain.member.enums.SocialType;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseErrorCode;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.security.service.CustomUserDetailsService;
import umc.server.global.security.util.JwtUtil;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain //다음 필터로 넘김
    ) throws ServletException, IOException{

        try {
            // 토큰 가져오기, 사용자가 로그인한 사람인지
            String token = request.getHeader("Authorization");
            if (token == null || !token.startsWith("Bearer ")) {//곰 어흥, Bearer 형식이 아니면
                filterChain.doFilter(request, response);
                return;
            }
            // Bearer 이면 추출
            token = token.replace("Bearer ", "");
            //AccessToken 검증하기
            if (jwtUtil.isValid(token)) {

                //jwt 토큰에서 유저 정보 조화, UID, 소셜 로그인 타입 가져오기
                String uid = jwtUtil.getUid(token);
                SocialType socialType = jwtUtil.getSocialType(token);

                //인증 객체 생성: 이메일 찾은뒤, 인증 객체 생성
                UserDetails member = customUserDetailsService.loadUserByUidAndSocialType(socialType, uid);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        member,
                        null,
                       member.getAuthorities()
                );
                //인증 완료후 SecurityContextHolder에 넣기
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            ObjectMapper mapper = new ObjectMapper();
            BaseErrorCode code = GeneralErrorCode.UNAUTHORIZED;

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(code.getStatus().value());

            ApiResponse<Void> errorResponse = ApiResponse.onFailure(code,null);

            mapper.writeValue(response.getOutputStream(),errorResponse);

        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/oauth/")
                || path.startsWith("/login/oauth2/");
    }

}
