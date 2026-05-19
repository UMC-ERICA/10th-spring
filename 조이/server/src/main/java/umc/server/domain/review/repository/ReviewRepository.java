package umc.server.domain.review.repository;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 가게의 리뷰 목록 조회
    List<Review> findByStoreId(Long storeId);

    // 특정 멤버의 리뷰 목록 조회
    Slice<Review> findReviewByMemberId_AndIdLessThan(Long memberId, Long idCursor, PageRequest pageRequest);

    Slice<Review> findReviewByMemberId_IdOrderByIdDesc(Long memberId, PageRequest pageRequest);

    Slice<Review> findByMemberIdOrderByStarDesc(
            Long memberId,
            Pageable pageable
    );
}
