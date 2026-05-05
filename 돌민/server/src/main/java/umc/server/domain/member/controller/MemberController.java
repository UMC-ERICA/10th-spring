package umc.server.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.member.dto.response.MemberLocationResponse;
import umc.server.domain.member.dto.response.MemberPointResponse;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.notification.dto.response.CompletedMissionCountResponse;
import umc.server.global.apiPayload.ApiResponse;

import java.util.List;

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
                "상세주소"
        );

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{memberId}/point")
    public ApiResponse<MemberPointResponse> getMemberPoint(
            @PathVariable Long memberId
    ) {
        MemberPointResponse result = new MemberPointResponse(
                memberId,
                1000
        );

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{memberId}/missions/complete")
    public ApiResponse<CompletedMissionCountResponse> getCompletedMissionCount(
            @PathVariable Long memberId
    ) {
        CompletedMissionCountResponse result = new CompletedMissionCountResponse(
                memberId,
                5L
        );

        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{memberId}/missions")
    public ApiResponse<List<MemberMissionResponse>> getMemberMissions(
            @PathVariable Long memberId
    ) {
        List<MemberMissionResponse> result = List.of(
                new MemberMissionResponse(
                        1L,
                        10L,
                        "리뷰 작성하기",
                        "IN_PROGRESS",
                        100
                )
        );

        return ApiResponse.onSuccess(result);
    }
}