package umc.server.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class ReviewReqDTO {

    public record CreateReviewDTO(
            @Schema(description = "회원 아이디", example = "1")
            Long memberId,

            @Schema(description = "가게 아이디", example = "1")
            Long storeId,

            @Schema(description = "리뷰 별점", example = "4.5")
            BigDecimal rating,

            @Schema(description = "리뷰", example = "너무 맛있었어요!")
            String comment,

            @Schema(description = "사진 url", example = "chicken.jpg")
            String photoUrl
    ) {}
}
