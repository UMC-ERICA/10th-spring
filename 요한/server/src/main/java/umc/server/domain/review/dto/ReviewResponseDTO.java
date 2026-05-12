package umc.server.domain.review.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    public record ReviewPreviewDTO(
            Long reviewId,
            String storeName,
            String content,
            Integer rating,
            LocalDateTime createdAt
    ) {
    }

    public record ReviewListDTO(
            List<ReviewPreviewDTO> reviewList,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
    }
}
