package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.converter.MemberConverter;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.security.entity.CustomUserDetails;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.JoinResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.JoinDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberService.join(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResultDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginDTO request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.login(request));
    }

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetProfileResultDTO> findProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getProfile(userDetails.getMember().getId()));
    }
}
