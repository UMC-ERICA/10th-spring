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
import umc.server.global.security.exception.CustomAccessDenied;
import umc.server.global.security.exception.CustomEntryPoint;
import umc.server.global.security.filter.JwtAuthFilter;
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

    private final String[] allowUris = {
            // Swagger 허용
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/auth/**",

            "/api/auth/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(allowUris).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true)
                        .permitAll()
                )
                .sessionManagement(AbstractHttpConfigurer::disable)
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDenied())
                        .authenticationEntryPoint(customEntryPoint())
                )
                .oauth2Login(oauth -> oauth
                        .authorizationEndpoint(auth -> auth
                                .baseUri("/oauth/authorize")
                        )
                        .redirectionEndpoint(redirect -> redirect
                                .baseUri("/oauth/callback/**")
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuthService)
                        )
                        .successHandler(oAuthSuccessHandler()));

        return http.build();
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
    public CustomEntryPoint customEntryPoint(){
        return new CustomEntryPoint();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(){
        return new JwtAuthFilter(jwtUtil,customUserDetailsService);
    }

    @Bean
    public OAuthSuccessHandler oAuthSuccessHandler(){
        return new OAuthSuccessHandler(jwtUtil);
    }
}
