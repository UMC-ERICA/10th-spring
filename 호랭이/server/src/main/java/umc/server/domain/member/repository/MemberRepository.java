package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
    void deleteByName(String name);
}
