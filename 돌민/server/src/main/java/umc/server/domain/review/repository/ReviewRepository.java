package umc.server.domain.review.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.review.dto.response.ReviewResponse;
import umc.server.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select count(r)
            from Review r
            where r.member.id = :memberId
              and r.deletedAt is null
            """)
    long countByMemberId(@Param("memberId") Long memberId);

    /**
     * [ID 정렬] 내가 작성한 리뷰 목록 조회 (최신순)
     */
    @Query("""
            select new umc.server.domain.review.dto.response.ReviewResponse.MyReview(
                r.id,
                s.name,
                r.star,
                r.content,
                r.createdAt
            )
            from Review r
            join r.store s
            where r.member.id = :memberId
              and r.deletedAt is null
              and s.deletedAt is null
              and (:cursorId is null or r.id < :cursorId)
            order by r.id desc
            """)
    List<ReviewResponse.MyReview> findByMemberIdOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    /**
     * [STAR 정렬] 내가 작성한 리뷰 목록 조회 (별점 높은 순, 같은 별점이면 최신순)
     */
    @Query("""
            select new umc.server.domain.review.dto.response.ReviewResponse.MyReview(
                r.id,
                s.name,
                r.star,
                r.content,
                r.createdAt
            )
            from Review r
            join r.store s
            where r.member.id = :memberId
              and r.deletedAt is null
              and s.deletedAt is null
              and (:cursorStar is null
                   or r.star < :cursorStar
                   or (r.star = :cursorStar and r.id < :cursorId))
            order by r.star desc, r.id desc
            """)
    List<ReviewResponse.MyReview> findByMemberIdOrderByStarDesc(
            @Param("memberId") Long memberId,
            @Param("cursorStar") Double cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
