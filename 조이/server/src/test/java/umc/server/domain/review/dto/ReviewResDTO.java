package umc.server.domain.review.dto;

import java.time.LocalDateTime;

public class ReviewResDTO {
    public record CreateResDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    public record CommentResDTO(
            Long commentId,
            LocalDateTime createdAt
    ) {
    }
}
