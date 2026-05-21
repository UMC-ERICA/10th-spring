package umc.server.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            @NotNull(message = "회원 아이디는 필수입니다.")
            @Schema(description = "회원 아이디", example = "1")
            Long memberId,

            @NotNull(message = "가게 아이디는 필수입니다.")
            @Schema(description = "가게 아이디", example = "1")
            Long storeId,

            @NotNull(message = "리뷰 별점은 필수입니다.")
            @Schema(description = "리뷰 별점", example = "4.5")
            BigDecimal rating,

            @Schema(description = "리뷰", example = "너무 맛있었어요!")
            String comment,

            @Schema(description = "사진 url", example = "chicken.jpg")
            String photoUrl
    ) {}
}
