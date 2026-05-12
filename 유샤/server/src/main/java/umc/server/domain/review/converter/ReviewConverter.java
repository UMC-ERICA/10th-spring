package umc.server.domain.review.converter;

import umc.server.domain.review.dto.ReviewResDTO;
import umc.server.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.PostReview toPostReviewResult(
            Review review
    ){
        return ReviewResDTO.PostReview.builder()
                .id(review.getId())
                .build();
    }

    public static ReviewResDTO.GetReview toGetReview(
            Review review
    ){
        return ReviewResDTO.GetReview.builder()
                .id(review.getId())
                .content(review.getContent())
                .star(review.getStar())
                .build();
    }

    public static <T> ReviewResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
