package umc.server.domain.review.dto.request;

public record ReviewCreateRequest(
        Double star,
        String content
) {
}
