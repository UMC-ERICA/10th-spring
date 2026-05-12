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
    public record GetReviewListDTO<T>(
            List<T> reviewList,           // 실제 데이터 리스트 (ReviewDTO 등)
            Long nextCursorId,            // 다음 요청에 사용할 ID 커서
            BigDecimal nextCursorRating,  // 다음 요청에 사용할 별점 커서
            Boolean hasNext               // 다음 페이지 존재 여부
    ) {}
}
