package umc.server.domain.review.dto;

public class ReviewReqDTO {

    public record PostReview(
            Integer star,
            String content
    ){}
}
