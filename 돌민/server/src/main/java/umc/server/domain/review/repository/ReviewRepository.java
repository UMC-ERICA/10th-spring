package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
            select count(r)
            from Review r
            where r.member.id = :memberId
              and r.deletedAt is null
            """)
    long countByMemberId(@Param("memberId") Long memberId);
}
