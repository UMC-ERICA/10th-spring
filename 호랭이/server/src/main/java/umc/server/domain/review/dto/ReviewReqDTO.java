package umc.server.domain.review.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {



    public record CreateReview(
            @NotNull(message = "내용은 필수입니다.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            @Min(value = 0, message = "별점은 0 이상이어야 합니다.")
            @Max(value = 5, message = "별점은 5 이하여야 합니다.")
            Float score
            // String photoUrl
    ) {}

    public record CreateReply(
            @NotNull(message = "답글 내용은 필수입니다.")
            String content
    ) {}
}