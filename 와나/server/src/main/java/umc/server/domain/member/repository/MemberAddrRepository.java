package umc.server.domain.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.MemberAddr;

public interface MemberAddrRepository extends JpaRepository<MemberAddr, Long> {
    Optional<MemberAddr> findByMemberId(Long memberId);
}
