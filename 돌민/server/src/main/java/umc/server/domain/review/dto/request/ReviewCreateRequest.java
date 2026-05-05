package umc.server.domain.review.dto.request;

import java.util.List;

public record ReviewCreateRequest(
        Double star,
        String content,
        List<String> image
) {
}