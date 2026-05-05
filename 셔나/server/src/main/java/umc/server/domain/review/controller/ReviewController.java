package umc.server.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> addReview(
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.CREATED, reviewService.createReview(request));
    }

    @GetMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.GetReviewListDTO> findReviewList(
            @PathVariable Long storeId
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewService.getReviewList(storeId));
    }
}
