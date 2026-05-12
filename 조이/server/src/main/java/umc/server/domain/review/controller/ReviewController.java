package umc.server.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.exception.code.ReviewSuccessCode;
import umc.server.domain.review.service.ReviewService;
import umc.server.global.apiPayload.ApiResponse;
import umc.server.global.apiPayload.code.BaseSuccessCode;
import umc.server.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResDTO.CreateResDTO> createReview(
            @RequestBody ReviewReqDTO.CreateReviewReqDTO dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }

    //사장님 댓글 작성
    @PostMapping("/{reviewId}/comments")
    public ApiResponse<ReviewResDTO.CommentResDTO> createComment(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.ReviewCommentReqDTO dto) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }


    //가게별 리뷰조회
    @GetMapping("/stores/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewPreViewListDTO> getReviewListByStore(
            @PathVariable(name = "storeId") Long storeId) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null); // 가게 컨트롤러에 넣는 게 좋을까요?!?!?
    }

    //내가 쓴 리뷰 조회
    @GetMapping("/member/{memberId}")
    public ApiResponse<ReviewResDTO.Pagination<ReviewResDTO.ReviewGetDTO>> getReviewListByMember(
            @PathVariable(name = "memberId") Long memberId,
            @RequestBody Integer pageSize,
            @RequestBody String cursor,
            @RequestBody String query

    ) {
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, reviewService.getReviewList(memberId, pageSize, cursor, query));
    }

}
