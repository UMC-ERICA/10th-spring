package umc.server.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

public class ReviewResDTO {

    @Builder
    public record CreateResDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CommentResDTO(
            Long commentId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewPreViewListDTO(
            List<ReviewGetDTO> reviews
    ) {
    }

    @Builder
    public record ReviewGetDTO(
            Long reviewId,
            String content,
            Double star,
            String imageUrl) {
    }
}
