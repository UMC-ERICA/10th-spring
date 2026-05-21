package umc.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;

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

    @GetMapping("/{memberId}")
    public ApiResponse<MemberResDTO.GetProfileResultDTO> findProfile(
            @PathVariable(name = "memberId") Long memberId
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, memberService.getProfile(memberId));
    }
}
