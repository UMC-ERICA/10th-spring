package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.request.ReviewCreateRequest;
import umc.server.global.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class ReviewController {

    @PostMapping("/{missionId}/review")
    public ApiResponse<Void> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewCreateRequest request
    ) {
        return ApiResponse.onSuccess();
    }
}