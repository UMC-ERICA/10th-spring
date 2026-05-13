package umc.server.domain.review.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // ID 오름차순: cursor 이후 항목 조회
    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant WHERE r.member = :member AND (:cursorId IS NULL OR r.id > :cursorId) ORDER BY r.id ASC")
    List<Review> findByMemberOrderById(
            @Param("member") Member member,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // 별점 내림차순: (score, id) 복합 커서 기반 조회
    @Query("SELECT r FROM Review r JOIN FETCH r.restaurant WHERE r.member = :member AND (:cursorScore IS NULL OR r.score < :cursorScore OR (r.score = :cursorScore AND r.id < :cursorId)) ORDER BY r.score DESC, r.id DESC")
    List<Review> findByMemberOrderByScore(
            @Param("member") Member member,
            @Param("cursorScore") Float cursorScore,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
