package umc.server.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.PostReview>  postReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.PostReview dto
            ){

        ReviewResDTO.PostReview result = reviewService.createReview(storeId, dto);
        BaseSuccessCode code = ReviewSuccessCode.CREATED;

        return ApiResponse.onSuccess(code,result);
    }

}
