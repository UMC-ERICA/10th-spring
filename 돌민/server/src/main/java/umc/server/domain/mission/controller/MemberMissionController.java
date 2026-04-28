package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.notification.dto.response.CompletedMissionCountResponse;
import umc.server.global.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members/{memberId}/missions")
public class MemberMissionController {

    @GetMapping("/complete")
    public ApiResponse<CompletedMissionCountResponse> getCompletedMissionCount(
            @PathVariable Long memberId
    ) {
        // TODO: memberMissionService.getCompletedMissionCount(memberId);

        CompletedMissionCountResponse result = new CompletedMissionCountResponse(
                memberId,
                5L
        );

        return ApiResponse.onSuccess(result);
    }

    @GetMapping
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