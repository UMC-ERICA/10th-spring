package umc.server.domain.review.dto;

public class ReviewRequestDTO {
    public record CreateReviewDTO(
            String content,
            Integer rating
    ) {
    }
}
