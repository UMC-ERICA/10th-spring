package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.SignUp> postSignup(
            @RequestBody @Valid MemberReqDTO.SignUp dto
    ){
        BaseSuccessCode code = MemberSuccessCode.CREATED;
        return ApiResponse.onSuccess(code,memberService.signUp(dto));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResDTO.Login> postLogin(
            @RequestBody @Valid MemberReqDTO.Login request
    ){
        BaseSuccessCode code = MemberSuccessCode.LOGIN_OK;
        return ApiResponse.onSuccess(code,memberService.login(request));
    }

}
