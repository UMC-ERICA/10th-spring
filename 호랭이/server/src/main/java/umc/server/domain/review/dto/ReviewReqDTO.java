package umc.server.domain.review.dto;

public class ReviewReqDTO {

    public record CreateReview(
            String content,
            Float score
           // String photoUrl

    ) {}

    public record CreateReply(
            String content
    ) {}
}