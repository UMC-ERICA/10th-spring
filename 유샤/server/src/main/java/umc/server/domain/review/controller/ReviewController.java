package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.GetReview>> getReviews(
            @RequestParam("memberId") Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        ReviewResDTO.Pagination<ReviewResDTO.GetReview> result = reviewService.findReviewsPage(memberId,pageSize,cursor,query);

        return ApiResponse.onSuccess(code,result);
    }
}
