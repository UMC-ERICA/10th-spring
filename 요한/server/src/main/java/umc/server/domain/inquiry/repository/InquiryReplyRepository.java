package umc.server.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.inquiry.entity.InquiryReply;

public interface InquiryReplyRepository extends JpaRepository<InquiryReply, Long> {
}
