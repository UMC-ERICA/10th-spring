package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.dto.request.CreateReviewRequest;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.CommonSuccessCode;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<Object> createReview(@PathVariable Long missionId,
                                            @RequestBody CreateReviewRequest request) {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        reviewService.createReview(memberId, missionId, request);
        return ApiResponse.success(CommonSuccessCode.CREATED, null);
    }
}
