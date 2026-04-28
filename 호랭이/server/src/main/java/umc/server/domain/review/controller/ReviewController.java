package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/store/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewList> getStoreReviews(
            @PathVariable Long storeId
    ) {
        return ApiResponse.onSuccess(reviewService.getStoreReviews(storeId));
    }

    @PostMapping("/{reviewId}/replies")
    public ApiResponse<String> createReply(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.CreateReply dto
    ) {
        return ApiResponse.onSuccess(reviewService.postReply(reviewId, dto));
    }
}