package umc.server.global.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import umc.server.domain.member.enums.SocialType;
import umc.server.global.security.entity.AuthMember;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

@Component //bean으로 등록
public class JwtUtil {

    private final SecretKey secretKey;
    private final Duration accessExpiration;

    public JwtUtil(
            @Value("${JWT_SECRET_KEY}") String secret,
            @Value("${jwt.token.expiration.access}") Long accessExpiration
    ){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpiration = Duration.ofMillis(accessExpiration);
    }

    //AccessToken 생성
    public String createAccessToken(AuthMember member){
        return createToken(member,accessExpiration);
    }

    //토큰에서 이메일 가져오기
    public String getEmail(String token){
        try{
            return getClaims(token).getPayload().getSubject();
        }catch (JwtException e){
            return null; //문제있는 토큰이면 반환값 없음
        }
    }

    //토크 유효성
    public boolean isValid(String token){
        try{
            getClaims(token); //호출 자체가 검증
            return true; //유효한 토큰
        }catch (JwtException e){
            return false;
        }
    }

    //토큰 생성
    private String createToken(AuthMember member, Duration expiration) {
        Instant now = Instant.now();

        //인가 정보
        String authorities = member.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .subject(member.getUsername())
                .claim("role", authorities)
                .claim("social_type", member.getMember().getSocialType())
                .issuedAt(Date.from(now))// 언제 발급한지
                .expiration(Date.from(now.plus(expiration)))
                .signWith(secretKey) //사인해야할 키
                .compact();
    }

    //토큰 정보 가져오기
    private Jws<Claims> getClaims(String token) throws JwtException{
        return Jwts.parser()
                .verifyWith(secretKey)
                .clockSkewSeconds(60)
                .build()
                .parseSignedClaims(token);

    }

    //토큰에서 소셜 로그인 타입 가져오기
    public SocialType getSocialType(String token) {
        try {
            return SocialType.valueOf(getClaims(token).getPayload().get("social_type").toString().toUpperCase());
        } catch (JwtException e){
            return null;
        }

    }


    public String getUid(String token) {
        return getClaims(token).getPayload().getSubject();
    }
}
