package umc.server.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.global.security.controller.AuthController;

@Service
@RequiredArgsConstructor
public class AuthService {

    public AuthController.TokenResponseDTO login(AuthController.LoginRequestDTO request) {

        return AuthController.TokenResponseDTO.builder()
                .accessToken("임시토큰")
                .build();
    }
}
