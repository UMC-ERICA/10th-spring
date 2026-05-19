package umc.server.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {
    public record CreateReviewReqDTO(
            @NotBlank(message = "리뷰 내용은 빈칸일 수 없습니다.")
            String content,
            @Min(0) @Max(5)
            @NotNull(message = "별점은 빈칸일 수 없습니다.")
            Double star,
            String imageUrl
    ) {
    }

    public record ReviewCommentReqDTO(
            @NotBlank(message = "리뷰 댓글은 빈칸일 수 없습니다.")
            String reviewComment
    ) {
    }


}
