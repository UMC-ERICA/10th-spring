package umc.server.domain.review.converter;

import java.util.List;
import java.util.stream.Collectors;
import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;
import umc.server.domain.store.dto.StoreResDTO;
import umc.server.domain.store.entity.Store;

public class ReviewConverter {
    //가게 리뷰 가져오기
    public static ReviewResDTO.ReviewGetDTO toReviewGetDTO(Review review) {
        return ReviewResDTO.ReviewGetDTO.builder()
                .reviewId(review.getId())
                .content(review.getReviewContent())
                .star(review.getStar())
                .imageUrl(review.getImageUrl())
                .build();
    }
    public static ReviewResDTO.ReviewPreViewListDTO toReviewPreViewListDTO(List<Review> reviews) {

        List<ReviewResDTO.ReviewGetDTO> reviewGetDTOList = reviews.stream()
                .map(ReviewConverter::toReviewGetDTO) // 위에서 만든 단일 변환 메서드 활용
                .collect(Collectors.toList());

        return ReviewResDTO.ReviewPreViewListDTO.builder()
                .reviews(reviewGetDTOList)
                .build();
}
