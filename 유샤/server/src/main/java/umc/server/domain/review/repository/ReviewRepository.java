package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findReviewsByMember_IdOrderByIdDesc(Long memberId, Pageable pageable);

    Slice<Review> findReviewsByMember_IdAndIdLessThanOrderByIdDesc(Long memberId, long idCursor,Pageable pageable);
    
    @Query("SELECT r FROM Review r WHERE r.member.id = :memberId " +
            "AND (r.star < :star OR (r.star = :star AND r.id < :id)) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findReviewsByMember_IdAndStarCursor(
            @Param("memberId") Long memberId,
            @Param("star") Integer star,
            @Param("id") Long id,
            Pageable pageable
    );
}
