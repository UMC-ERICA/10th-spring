package umc.server.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record PostReview(
            Long id
    ){}

    @Builder
    public record GetReview(
            Long id,
            String content,
            Integer star
    ){}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}
}
