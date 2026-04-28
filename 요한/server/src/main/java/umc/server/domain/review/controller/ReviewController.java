package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.dto.ReviewResponseDTO;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-missions")
public class ReviewController {

    @PostMapping("/{userMissionId}/reviews")
    public ApiResponse<ReviewResponseDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "userMissionId") Long userMissionId,
            @RequestBody ReviewRequestDTO.CreateReviewDTO request) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
