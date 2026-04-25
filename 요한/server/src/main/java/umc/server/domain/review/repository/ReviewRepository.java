package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
