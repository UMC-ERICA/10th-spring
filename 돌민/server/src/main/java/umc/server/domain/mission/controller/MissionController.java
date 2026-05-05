package umc.server.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.mission.dto.request.MissionSearchRequest;
import umc.server.domain.mission.dto.request.MissionStatusUpdateRequest;
import umc.server.domain.mission.dto.response.MissionResponse;
import umc.server.domain.review.dto.request.ReviewCreateRequest;
import umc.server.global.apiPayload.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<List<MissionResponse>> getMissions(
            @ModelAttribute MissionSearchRequest request
    ) {
        List<MissionResponse> result = List.of(
                new MissionResponse(
                        1L,
                        "리뷰 작성하기",
                        "가게 방문 후 리뷰를 작성하세요.",
                        100,
                        "IN_PROGRESS"
                )
        );

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
            @RequestBody ReviewCreateRequest request
    ) {
        return ApiResponse.onSuccess();
    }
}