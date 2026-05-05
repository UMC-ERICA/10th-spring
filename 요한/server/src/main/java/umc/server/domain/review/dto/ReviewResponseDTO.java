package umc.server.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResponseDTO {
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }
}
