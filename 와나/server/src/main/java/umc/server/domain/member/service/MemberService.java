package umc.server.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.dto.request.SignupRequest;

@Service
@RequiredArgsConstructor
public class MemberService {
    public Long signUp(SignupRequest request) {
        // TODO : 추후 구현
        return null;
    }
}
