package umc.server.domain.review.converter;

import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.dto.ReviewResponseDTO;
import umc.server.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.CreateReviewDTO request, MemberMission memberMission) {
        return Review.builder()
                .rating(request.rating())
                .content(request.content())
                .member(memberMission.getMember())
                .store(memberMission.getMission().getStore())
                .build();
    }

    public static ReviewResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return new ReviewResponseDTO.CreateReviewResultDTO(
                review.getId(),
                review.getCreatedAt()
        );
    }
}
