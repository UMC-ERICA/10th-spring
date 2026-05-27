package umc.server.domain.member.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
