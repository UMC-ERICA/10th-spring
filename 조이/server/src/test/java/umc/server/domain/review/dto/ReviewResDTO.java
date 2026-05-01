package umc.server.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

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

    public record ReviewStoreGetDTO(
            List<ReviewGetDTO> reviews
    ) {
    }

    public record ReviewGetDTO(
            Long reviewId,
            String content,
            Double star,
            String imageUrl) {
    }
}
