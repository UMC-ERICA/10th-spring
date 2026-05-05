package umc.server.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResultDTO(
            BigDecimal rating,
            String comment
    ) {}
}
