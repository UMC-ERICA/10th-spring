package umc.server.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.dto.request.MyReviewRequest;
import umc.server.domain.review.dto.response.ReviewResponse;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

/**
 * 리뷰 생성은 MissionController에 있고 (미션 완료와 함께 리뷰 작성),
 * 리뷰 조회는 여기(ReviewController)에 있습니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * 내가 작성한 리뷰 목록 조회 (커서 기반 페이지네이션)
     */
    @PostMapping("/my")
    public ApiResponse<ReviewResponse.CursorResult> getMyReviews(
            @Valid @RequestBody MyReviewRequest request
    ) {
        return ApiResponse.onSuccess(reviewService.getMyReviews(request));
    }
}
