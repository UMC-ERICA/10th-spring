package umc.server.domain.review.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 리뷰 작성 요청 DTO
 * @DecimalMin  → Double/Float 타입의 최솟값 (inclusive = 경계값 포함 여부)
 * @DecimalMax  → Double/Float 타입의 최댓값
 */
public record ReviewCreateRequest(

        @NotNull(message = "별점은 필수입니다.")
        @DecimalMin(value = "1.0", message = "별점은 1.0 이상이어야 합니다.")
        @DecimalMax(value = "5.0", message = "별점은 5.0 이하여야 합니다.")
        Double star,

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String content
) {
}
