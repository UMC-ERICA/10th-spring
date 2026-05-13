package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.dto.request.CreateReviewRequest;
import umc.server.domain.review.dto.response.GetMyReviewsResponse;
import umc.server.domain.review.entity.enums.ReviewSortType;
import umc.server.domain.review.service.ReviewQueryService;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.exception.apiPayload.ApiResponse;
import umc.server.global.exception.code.CommonSuccessCode;
import umc.server.global.paging.CursorPageResponse;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewQueryService reviewQueryService;

    @PostMapping("/missions/{missionId}/reviews")
    public ApiResponse<Object> createReview(@PathVariable Long missionId,
                                            @RequestBody CreateReviewRequest request) {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        reviewService.createReview(memberId, missionId, request);
        return ApiResponse.success(CommonSuccessCode.CREATED, null);
    }

    // 내가 생성한 리뷰 조회 (커서 기반, ID순/별점순)
    @GetMapping("/reviews/my")
    public ApiResponse<CursorPageResponse<GetMyReviewsResponse>> getMyReviews(
            @RequestParam(defaultValue = "ID") ReviewSortType sortType,
            @RequestParam(required = false) String cursor
    ) {
        // 추후 Security를 통해 자신의 memberId를 가져옴. 지금은 임시로 1L 사용
        Long memberId = 1L;
        return ApiResponse.success(CommonSuccessCode.OK,
                reviewQueryService.getMyReviews(memberId, sortType, cursor));
    }
}
