package umc.server.domain.review.dto;

import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record PostReview(
            @NotNull(message="별점은 필수입니다.")
            Integer star,
            String content
    ){}
}
