package umc.server.domain.review.converter;

import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.entity.ReviewPhoto;

import java.util.ArrayList;

public class ReviewConverter {

    // DTO -> entity
    public static Review toReview(ReviewReqDTO.CreateReviewDTO request) {
        Review review = Review.builder()
                .rating(request.rating())
                .comment(request.comment())
                .reviewPhotoList(new ArrayList<>())
                .build();

        if (request.photoUrl() != null && !request.photoUrl().isBlank()) {
            ReviewPhoto photo = ReviewPhoto.builder()
                    .photoUrl(request.photoUrl())
                    .build();
            review.addReviewPhoto(photo);
        }

        return review;
    }

    // entity -> 리뷰 작성 DTO
    public static ReviewResDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return ReviewResDTO.CreateReviewResultDTO.builder()
                .rating(review.getRating())
                .comment(review.getComment())
                .build();
    }
}
