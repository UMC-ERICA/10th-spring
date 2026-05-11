package umc.server.domain.review.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record ReviewDTO(
            Long reviewId,
            String nickname,
            BigDecimal rating,
            String comment,
            List<String> photoUrls,
            LocalDateTime createdAt,
            List<String> reviewReplies  // 사장님이 단 답글이 있다면 함께 반환
    ) {}

    @Builder
    public record GetReviewListDTO(
            List<ReviewDTO> reviewList
    ) {}
}
