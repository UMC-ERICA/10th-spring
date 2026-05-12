package umc.server.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    public record CreateReviewReqDTO(
            @NotNull(message = "리뷰 내용은 빈칸일 수 없습니다.")
            String content,
            @Min(0) @Max(5)
            @NotNull(message = "별점은 빈칸일 수 없습니다.")
            Double star,
            String imageUrl
    ) {
    }

    public record ReviewCommentReqDTO(
            @NotNull(message = "리뷰 댓글은 빈칸일 수 없습니다.")
            String reviewComment
    ) {
    }


}
