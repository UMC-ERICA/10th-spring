package umc.server.domain.review.dto.response;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 리뷰 응답 DTO 모음
 */
public class ReviewResponse {

    /**
     * 리뷰 단건 조회 응답
     */
    public record MyReview(
            Long reviewId,
            String storeName,
            Double star,
            String content,
            LocalDateTime createdAt
    ) {
    }

    /**
     * 커서 기반 페이지네이션 응답
     */
    public record CursorResult(
            List<MyReview> reviews,
            boolean hasNext,
            Long nextCursorId,
            Double nextCursorStar
    ) {
    }
}
