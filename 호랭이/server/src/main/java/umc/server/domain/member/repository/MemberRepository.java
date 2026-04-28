package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>{
    void deleteByName(String name);
}
