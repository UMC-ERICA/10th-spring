package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.MemberAgreement;

public interface MemberAgreementRepository extends JpaRepository<MemberAgreement, Long> {
}
