package umc.server.domain.review.controller;

import jakarta.validation.Valid;
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
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/missions/{userMissionId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "userMissionId") Long userMissionId,
            @RequestBody @Valid ReviewRequestDTO.CreateReviewDTO request) {

        Review review = reviewService.createReview(userMissionId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, ReviewConverter.toCreateReviewResultDTO(review));
    }

    // 내가 생성한 리뷰 목록 조회 (커서 기반 페이징)
    @PostMapping("/me")
    public ApiResponse<ReviewResponseDTO.ReviewListDTO> getMyReviews(
            @RequestBody @Valid ReviewRequestDTO.ReviewListRequestDTO request) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getReviewList(request));
    }
}
