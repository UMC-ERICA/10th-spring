package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
