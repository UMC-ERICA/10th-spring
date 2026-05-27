package umc.server.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.request.MyMissionRequest;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.mission.service.MissionService;
import umc.server.domain.review.dto.request.ReviewCreateRequest;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.PageResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;
    private final ReviewService reviewService;

    /**
     * 내가 진행중인 미션 목록 조회 (오프셋 기반 페이지네이션)
     */
    @PostMapping("/my")
    public ApiResponse<PageResponse<MemberMissionResponse>> getMyMissions(
            @Valid @RequestBody MyMissionRequest request
    ) {
        return ApiResponse.onSuccess(missionService.getMyMissions(request));
    }

    /**
     * 미션에 대한 리뷰 작성
     * @RequestHeader가 좀... 뭐랄까.. 왜 있는걸까 싶음
     */
    @PostMapping("/{missionId}/review")
    public ApiResponse<Void> createReview(
            @PathVariable Long missionId,
            @RequestHeader("memberId") Long memberId,
            @Valid @RequestBody ReviewCreateRequest request
    ) {
        reviewService.createReview(missionId, memberId, request);
        return ApiResponse.onSuccess();
    }
}
