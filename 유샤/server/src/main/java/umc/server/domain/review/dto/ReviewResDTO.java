package umc.server.domain.review.dto;

import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record PostReview(
            Long id
    ){}
}
