package umc.server.domain.review.dto.request;

public record CreateReviewRequest(
        float score,
        String content,
        String imageUrl
) {
}
