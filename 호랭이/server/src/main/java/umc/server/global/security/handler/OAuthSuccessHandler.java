package umc.server.global.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.security.entity.AuthMember;
import umc.server.global.security.entity.OAuthMember;
import umc.server.global.security.util.JwtUtil;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication //누가 로그인했는지 정보
    ) throws IOException, ServletException{
        ObjectMapper objectMapper = new ObjectMapper(); //json, 자바 객체 변환기
        BaseSuccessCode code = MemberSuccessCode.OK;

        // content-Type, Statue 설정
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(code.getStatus().value());

        //OAuth 인증 객체 가져오기
        OAuthMember member = (OAuthMember) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //인증 객체에서 member 추출, AuthMember 제작, 토큰 제작 위해서
        String accessToken = jwtUtil.createAccessToken(new AuthMember(member.getMember()));

        //응답 통일 객체 래핑
        ApiResponse<MemberResDTO.Login> responseBody = ApiResponse.onSuccess(
                code,
                MemberConverter.toLogin(accessToken)
        );

        //응답 출력
        objectMapper.writeValue(response.getOutputStream(),responseBody);
    }
}
