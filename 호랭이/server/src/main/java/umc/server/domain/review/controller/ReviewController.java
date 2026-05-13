package umc.server.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}")
    public ApiResponse<Void> createdReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReview request
    ) {
        reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(null);
    }
}