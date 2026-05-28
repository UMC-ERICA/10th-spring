package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.request.LoginRequest;
import umc.server.domain.member.dto.request.SignupRequest;
import umc.server.domain.member.dto.response.GetProfileResponse;
import umc.server.domain.member.dto.response.LoginResponse;
import umc.server.domain.member.dto.response.MySimpleAddressResponse;
import umc.server.domain.member.service.AuthService;
import umc.server.domain.member.service.MemberAddrService;
import umc.server.domain.member.service.MemberService;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.CommonSuccessCode;
import umc.server.global.security.entity.AuthMember;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final AuthService authService;
    private final MemberService memberService;
    private final MemberAddrService memberAddrService;

    @PostMapping("/auth/signup")
    public ApiResponse<Long> signUp(
            @RequestBody SignupRequest request
    ) {
        authService.signUp(request);
        return ApiResponse.success(CommonSuccessCode.CREATED, null);
    }

    @PostMapping("/auth/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ApiResponse.success(CommonSuccessCode.OK, authService.login(request));
    }

    @GetMapping("/members/my")
    public ApiResponse<GetProfileResponse> getMyInfo(
            @AuthenticationPrincipal AuthMember member
            ) {
        return ApiResponse.success(CommonSuccessCode.OK, memberService.getProfile(member.getMember().getId()));
    }



    // 홈화면 - 내 지역 단순 조회
    @GetMapping("/locations/my")
    public ApiResponse<MySimpleAddressResponse> getMySimpleAddress(
            @AuthenticationPrincipal AuthMember member
    ) {
        return ApiResponse.success(CommonSuccessCode.OK, memberAddrService.getSimpleAddress(member.getMember().getId()));
    }

}
