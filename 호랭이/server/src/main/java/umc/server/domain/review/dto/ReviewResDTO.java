package umc.server.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewInfo(
            String nickname,
            Float score,
            String content,
            //String photoUrl,
            LocalDate createdAt,
            String reply
    ) {}

    //리뷰 목록
    @Builder
    public record ReviewList(
            List<ReviewInfo> reviews
    ) {}

    //페이지네이션
    @Builder
    public record CursorPage<T>(
            List<T> data,
            Long nextCursor,//다음 커서
            Boolean hasNext // 다음 있음?
    ){}


}