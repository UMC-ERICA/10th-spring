package umc.server.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

import java.math.BigDecimal;

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
    public ApiResponse<ReviewResDTO.GetReviewListDTO<ReviewResDTO.ReviewDTO>> findReviewList(
            @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "cursorId", required = false) Long cursorId,
            @RequestParam(name = "cursorRating", required = false) BigDecimal cursorRating,
            @RequestParam(name = "sortBy", defaultValue = "RATING") String sortBy,  // RATING 또는 ID
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, reviewService.getReviewList(storeId, cursorId, cursorRating, sortBy, size));
    }
}
