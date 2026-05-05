package umc.server.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ReviewReqDTO {
    public record CreateReviewReqDTO(
            String content,
            @Min(0) @Max(5)
            Double star,
            String imageUrl
    ) {
    }

    public record ReviewCommentReqDTO(
            String reviewComment
    ) {
    }


}
