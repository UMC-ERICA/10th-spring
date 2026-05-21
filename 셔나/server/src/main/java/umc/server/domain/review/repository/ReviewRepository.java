package umc.server.domain.review.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.review.entity.Review;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 별점순 정렬 커서 페이징
    @Query("SELECT DISTINCT r FROM Review r " +
            "JOIN FETCH r.member " +
            "LEFT JOIN FETCH r.replyList " +
            "WHERE r.store.id = :storeId " +
            "AND (:cursorRating IS NULL OR r.rating < :cursorRating " +
            "OR (r.rating = :cursorRating AND r.id < :cursorId)) " +
            "ORDER BY r.rating DESC, r.id DESC")
    List<Review> findMyReviewsByRatingDesc(
            @Param("storeId") Long storeId,
            @Param("cursorRating") BigDecimal cursorRating,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    // ID순 정렬 커서 페이징
    @Query("SELECT DISTINCT r FROM Review r " +
            "JOIN FETCH r.member " +
            "LEFT JOIN FETCH r.replyList " +
            "WHERE r.store.id = :storeId " +
            "AND (:cursorId IS NULL OR r.id < :cursorId) " +
            "ORDER BY r.id DESC")
    List<Review> findMyReviewsByIdDesc(
            @Param("storeId") Long storeId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}
