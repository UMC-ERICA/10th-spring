package umc.server.domain.review.dto.response;

import umc.server.domain.review.entity.Review;

public record GetMyReviewsResponse(
        Long reviewId,
        String restaurantName,
        float score,
        String content
) {
    public static GetMyReviewsResponse from(Review review) {
        return new GetMyReviewsResponse(
                review.getId(),
                review.getRestaurant().getName(),
                review.getScore(),
                review.getContent()
        );
    }
}
