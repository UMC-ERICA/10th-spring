package umc.server.domain.inquiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.inquiry.entity.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    @Query("""
            select count(i)
            from Inquiry i
            where i.member.id = :memberId
              and i.deletedAt is null
            """)
    long countByMemberId(@Param("memberId") Long memberId);
}
