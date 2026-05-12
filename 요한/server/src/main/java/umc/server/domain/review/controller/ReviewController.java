package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.dto.ReviewResponseDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-missions")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{userMissionId}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "userMissionId") Long userMissionId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {

        Review review = reviewService.createReview(userMissionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, ReviewConverter.toCreateReviewResultDTO(review));
    }
}
