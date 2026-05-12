package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.Code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}")
    public ApiResponse<Void> createdReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.CreateReview request
    ) {
        BaseSuccessCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, reviewService.createReview(storeId, request));
    }

    @GetMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CursorPage<ReviewResDTO.ReviewInfo>>getReview(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String sort
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code,
                reviewService.getReviews(storeId,pageSize,cursor,sort));

    }
}