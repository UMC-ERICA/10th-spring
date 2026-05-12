package umc.server.domain.review.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;
import umc.server.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Slice<Review> findAllByMemberOrderByIdDesc(Member member, Pageable pageable);

    Slice<Review> findAllByMemberAndIdLessThanOrderByIdDesc(Member member, Long id, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member = :member ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findAllByMemberOrderByRatingDescIdDesc(@Param("member") Member member, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.member = :member AND (r.rating < :rating OR (r.rating = :rating AND r.id < :id)) ORDER BY r.rating DESC, r.id DESC")
    Slice<Review> findAllByMemberAndRatingAndIdLessThanOrderByRatingDescIdDesc(
            @Param("member") Member member,
            @Param("rating") Integer rating,
            @Param("id") Long id,
            Pageable pageable
    );
}
