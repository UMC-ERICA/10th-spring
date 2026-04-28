package umc.server.domain.review.dto;

import lombok.Builder;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewInfo(
            String nickname,
            Float score,
            String content,
            String photoUrl,
            String reply
    ) {}

    @Builder
    public record ReviewList(
            List<ReviewInfo> reviews
    ) {}
}