package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{
    void deleteByName(String name);
    Optional<Member> findByEmail(String email); //회원 없을 수도 있으므로 예외처리 , 이메일은 기본제공 아님
}
