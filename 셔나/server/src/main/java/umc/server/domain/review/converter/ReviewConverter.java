package umc.server.domain.review.converter;

import umc.server.domain.review.dto.ReviewReqDTO;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Reply;
import umc.server.domain.review.entity.Review;
import umc.server.domain.review.entity.ReviewPhoto;

import java.util.ArrayList;
import java.util.List;

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
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // entity -> 리뷰 조회 DTO
    public static ReviewResDTO.ReviewDTO toReviewDTO(Review review) {
        List<String> reviewPhotoList = review.getReviewPhotoList().stream()
                .map(ReviewPhoto::getPhotoUrl)
                .toList();

        List<String> replyContents = review.getReplyList().stream()
                .map(Reply::getContent)
                .toList();

        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .nickname(review.getMember().getNickname())
                .rating(review.getRating())
                .comment(review.getComment())
                .photoUrls(reviewPhotoList)
                .createdAt(review.getCreatedAt())
                .reviewReplies(replyContents)
                .build();
    }

    // entity -> 리뷰 목록 조회 DTO
    public static ReviewResDTO.GetReviewListDTO toGetReviewResultDTO(List<Review> reviewList) {
        List<ReviewResDTO.ReviewDTO> reviewDTOList = reviewList.stream()
                .map(ReviewConverter::toReviewDTO)
                .toList();

        return ReviewResDTO.GetReviewListDTO.builder()
                .reviewList(reviewDTOList)
                .build();
    }
}
