package umc.server.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.server.global.security.filter.JwtAuthFilter;
import umc.server.global.security.handler.CustomAccessDenied;
import umc.server.global.security.handler.CustomEntryPoint;
import umc.server.global.security.handler.OAuthSuccessHandler;
import umc.server.global.security.service.CustomOAuthService;
import umc.server.global.security.service.CustomUserDetailsService;
import umc.server.global.security.util.JwtUtil;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomOAuthService customOAuthService;

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtUtil, customUserDetailsService);
    }

    private final String[] allowUris = {
            //
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",

            //로그인
            "/auth/**",
            "/members"

    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   CustomOAuthService customOAuthService,
                                                   CustomAccessDenied customAccessDenied,
                                                   CustomEntryPoint customEntryPoint) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(AbstractHttpConfigurer::disable
                )
                .sessionManagement(AbstractHttpConfigurer::disable)
                //JWT 필터
                .addFilterBefore(jwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                //OAuth
                .oauth2Login(oauth -> oauth
                        //인증 엔트리 포인트
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth/authorize")
                        )
                        //콜백 주소
                        .redirectionEndpoint(redirect->redirect
                                .baseUri("/oauth/callback/**")
                        )
                        //인증 완료 후 정보 활용
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )
                        //성공시 jWT 토큰 발생할 핸들러
                        .successHandler(oAuthSuccessHandler())
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler oAuthSuccessHandler() {
        return new OAuthSuccessHandler(jwtUtil);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAccessDenied customAccessDenied() {
        return new CustomAccessDenied();
    }

    @Bean
    public CustomEntryPoint customEntryPoint() {
        return new CustomEntryPoint();
    }
}