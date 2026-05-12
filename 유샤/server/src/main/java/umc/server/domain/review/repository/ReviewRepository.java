package umc.server.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r " +
            "WHERE r.member.id = :memberId ")
    Page<Review> findReviews(Long memberId, Pageable pageable);
}
