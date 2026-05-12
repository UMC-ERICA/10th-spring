package umc.server.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequestDTO {
    public record CreateReviewDTO(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content,
            @NotNull(message = "평점은 필수입니다.")
            @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
            @Max(value = 5, message = "평점은 최대 5점 이하여야 합니다.")
            Integer rating
    ) {
    }

    public record ReviewListRequestDTO(
            @NotNull(message = "사용자 ID는 필수입니다.")
            Long memberId,
            String cursor,
            @NotNull(message = "페이지 크기는 필수입니다.")
            @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
            Integer pageSize,
            @NotNull(message = "정렬 타입은 필수입니다.")
            String sortType // "ID" or "RATING"
    ) {
    }
}
