package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.dto.MemberResDTO.JoinResDTO;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;
import umc.server.global.security.entity.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<JoinResDTO> join(
            @RequestBody @Valid MemberReqDTO.JoinReqDTO dto
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, memberService.join(dto));
    }
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResDTO> login(
            @RequestBody @Valid MemberReqDTO.LoginReqDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN_SUCCESS, memberService.login(dto));
    }

    @PostMapping("/users/me")
    public ApiResponse<MemberResDTO> getInfo(
            @AuthenticationPrincipal AuthMember member
            ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }



}

