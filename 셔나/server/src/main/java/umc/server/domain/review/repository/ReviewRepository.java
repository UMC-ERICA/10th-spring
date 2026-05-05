package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import umc.server.domain.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN FETCH r.member LEFT JOIN FETCH r.replyList WHERE r.store.id = :storeId")
    List<Review> findAllByStoreIdWithReply(Long storeId);
}
