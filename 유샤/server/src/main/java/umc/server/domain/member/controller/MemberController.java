package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.exception.code.MemberSuccessCode;
import umc.server.domain.member.service.MemberService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.security.entity.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @AuthenticationPrincipal AuthMember member
            ){
        MemberResDTO.GetInfo result = memberService.getMe(member);
        BaseSuccessCode code = MemberSuccessCode.OK;

        return ApiResponse.onSuccess(code,result); // service 생성 후 넣을 예정
    }

    @GetMapping("/me/address")
    public ApiResponse<MemberResDTO.GetAddress> getAddress(
            @RequestParam Long id
    ){
        MemberResDTO.GetAddress result = memberService.getAddress();
        BaseSuccessCode code = MemberSuccessCode.ADDRESS_FOUND;
        return ApiResponse.onSuccess(code,result);
    }

}
