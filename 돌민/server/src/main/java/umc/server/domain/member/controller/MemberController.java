package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.request.HomeSearchRequest;
import umc.server.domain.member.dto.response.HomeResponse;
import umc.server.domain.member.dto.response.MemberLocationResponse;
import umc.server.domain.member.dto.response.MemberPointResponse;
import umc.server.domain.member.dto.response.MyPageResponse;
import umc.server.domain.member.service.MemberService;
import umc.server.domain.mission.dto.request.MemberMissionSearchRequest;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.notification.dto.response.CompletedMissionCountResponse;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.PageResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}/location")
    public ApiResponse<MemberLocationResponse> getMemberLocation(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(memberService.getMemberLocation(memberId));
    }

    @GetMapping("/{memberId}/point")
    public ApiResponse<MemberPointResponse> getMemberPoint(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(memberService.getMemberPoint(memberId));
    }

    @GetMapping("/{memberId}/missions/complete")
    public ApiResponse<CompletedMissionCountResponse> getCompletedMissionCount(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(memberService.getCompletedMissionCount(memberId));
    }

    @GetMapping("/{memberId}/missions")
    public ApiResponse<PageResponse<MemberMissionResponse>> getMemberMissions(
            @PathVariable Long memberId,
            @ModelAttribute MemberMissionSearchRequest request,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(memberService.getMemberMissions(memberId, request, pageable));
    }

    @GetMapping("/{memberId}/mypage")
    public ApiResponse<MyPageResponse> getMyPage(
            @PathVariable Long memberId
    ) {
        return ApiResponse.onSuccess(memberService.getMyPage(memberId));
    }

    @GetMapping("/{memberId}/home")
    public ApiResponse<HomeResponse> getHome(
            @PathVariable Long memberId,
            @ModelAttribute HomeSearchRequest request,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ApiResponse.onSuccess(memberService.getHome(memberId, request, pageable));
    }
}
