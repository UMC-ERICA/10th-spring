package umc.server.domain.review.converter;

import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;

public class ReviewConverter {

    public static ReviewResDTO.PostReview toPostReviewResult(
            Review review
    ){
        return ReviewResDTO.PostReview.builder()
                .id(review.getId())
                .build();
    }
}
