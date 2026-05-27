package umc.server.domain.review.converter;

import org.springframework.data.domain.Slice;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.review.dto.ReviewRequestDTO;
import umc.server.domain.review.dto.ReviewResponseDTO;
import umc.server.domain.review.entity.Review;

import java.util.List;
import java.util.stream.Collectors;

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

    public static ReviewResponseDTO.ReviewPreviewDTO toReviewPreviewDTO(Review review) {
        return new ReviewResponseDTO.ReviewPreviewDTO(
                review.getId(),
                review.getStore().getName(),
                review.getContent(),
                review.getRating(),
                review.getCreatedAt()
        );
    }

    public static ReviewResponseDTO.ReviewListDTO toReviewListDTO(Slice<Review> reviewSlice, String nextCursor, Integer pageSize) {
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewSlice.getContent().stream()
                .map(ReviewConverter::toReviewPreviewDTO)
                .collect(Collectors.toList());

        return new ReviewResponseDTO.ReviewListDTO(
                reviewPreviewDTOList,
                reviewSlice.hasNext(),
                nextCursor,
                pageSize
        );
    }
}
