package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.MemberRequestDTO;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;
import umc.server.global.security.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, memberService.join(request));
    }

    @PostMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.login(request));
    }

    @GetMapping("/me")
    public ApiResponse<MemberResponseDTO.MyPageDTO> getMyPage(@AuthenticationPrincipal AuthMember authMember) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.getMyPage(authMember.getMember().getId()));
    }

    @GetMapping("/me/home")
    public ApiResponse<MemberResponseDTO.HomeDTO> getHome(
            @AuthenticationPrincipal AuthMember authMember,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.getMemberHome(authMember.getMember().getId(), page));
    }
}
