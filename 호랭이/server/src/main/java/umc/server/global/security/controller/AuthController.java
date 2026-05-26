package umc.server.global.security.controller;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.security.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<TokenResponseDTO> login(@RequestBody LoginRequestDTO request){
        return ApiResponse.onSuccess(authService.login(request));
    }

    public record LoginRequestDTO(
            String email,
            String password
    ){}

    @Builder
    public record TokenResponseDTO(
            String accessToken
    ){}
}
