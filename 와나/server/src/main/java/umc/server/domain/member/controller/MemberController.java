package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.request.SignupRequest;
import umc.server.domain.member.dto.response.MySimpleAddressResponse;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.service.MemberAddrService;
import umc.server.domain.member.service.MemberService;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.CommonSuccessCode;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberAddrService memberAddrService;

    // 회원가입
    @PostMapping("/auth/signup")
    public ApiResponse<Long> signUp(
            @RequestBody SignupRequest request
    ) {
        Long memberId = memberService.signUp(request);
        return ApiResponse.success(CommonSuccessCode.CREATED, null);
    }


    // 홈화면 - 내 지역 단순 조회
    @GetMapping("/locations/my")
    public ApiResponse<MySimpleAddressResponse> getMySimpleAddress() {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        return ApiResponse.success(CommonSuccessCode.OK, memberAddrService.getSimpleAddress(memberId));
    }
}
