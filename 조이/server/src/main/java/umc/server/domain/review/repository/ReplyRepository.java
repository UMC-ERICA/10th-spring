package umc.server.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.review.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
