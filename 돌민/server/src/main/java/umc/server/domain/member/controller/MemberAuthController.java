package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.request.MemberSignUpRequest;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/members")
public class MemberAuthController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<Void> signUp(@Valid @RequestBody MemberSignUpRequest request) {
        memberService.signUp(request);
        return ApiResponse.onSuccess();
    }
}
