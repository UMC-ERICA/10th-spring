package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.review.entity.ReviewReply;

public interface ReviewReplyRepository extends JpaRepository<ReviewReply, Long> {
}
