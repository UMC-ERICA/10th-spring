package umc.server.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.review.enums.ReviewSortType;

/**
 * "내가 작성한 리뷰 조회" 요청 DTO (커서 기반 페이지네이션)
 */
public record MyReviewRequest(

        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "정렬 기준은 필수입니다. (ID 또는 STAR)")
        ReviewSortType sortBy,

        Long cursorId,
        Double cursorStar,

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        @Max(value = 50, message = "페이지 크기는 50 이하여야 합니다.")
        Integer size
) {
}
