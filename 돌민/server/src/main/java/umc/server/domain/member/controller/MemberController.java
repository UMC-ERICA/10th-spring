package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.response.MemberLocationResponse;
import umc.server.domain.member.dto.response.MemberPointResponse;
import umc.server.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    @GetMapping("/{memberId}/location")
    public ApiResponse<MemberLocationResponse> getMemberLocation(
            @PathVariable Long memberId
    ) {
        MemberLocationResponse result = new MemberLocationResponse(
                memberId,
                "서울특별시",
                "강남구",
                "역삼동",
                "123-45"
        );

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{memberId}/point")
    public ApiResponse<MemberPointResponse> getMemberPoint(
            @PathVariable Long memberId
    ) {
        // TODO: memberService.getMemberPoint(memberId);

        MemberPointResponse result = new MemberPointResponse(
                memberId,
                1000
        );

        return ApiResponse.onSuccess(result);
    }
}