package umc.server.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.inquiry.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}
