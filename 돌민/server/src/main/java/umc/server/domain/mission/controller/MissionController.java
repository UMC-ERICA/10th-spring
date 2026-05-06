package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.request.MissionSearchRequest;
import umc.server.domain.mission.dto.request.MissionStatusUpdateRequest;
import umc.server.domain.mission.dto.response.MissionResponse;
import umc.server.domain.review.dto.request.ReviewCreateRequest;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<MissionResponse>> getMissions(
            @ModelAttribute MissionSearchRequest request
    ) {
        List<MissionResponse> result = List.of();
        return ApiResponse.onSuccess(result);
    }

    @PatchMapping("/{missionId}/status")
    public ApiResponse<Void> updateMissionStatus(
            @PathVariable Long missionId,
            @RequestBody MissionStatusUpdateRequest request
    ) {
        return ApiResponse.onSuccess();
    }

    @PostMapping("/{missionId}/review")
    public ApiResponse<Void> createReview(
            @PathVariable Long missionId,
            @RequestHeader("memberId") Long memberId,
            @RequestBody ReviewCreateRequest request
    ) {
        reviewService.createReview(missionId, memberId, request);
        return ApiResponse.onSuccess();
    }
}
