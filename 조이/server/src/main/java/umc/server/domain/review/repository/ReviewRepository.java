package umc.server.domain.review.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 가게의 리뷰 목록 조회
    List<Review> findByStoreId(Long storeId);
}
