package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.MemberTermCondition;

public interface MemberTermConditionRepository extends JpaRepository<MemberTermCondition, Long> {
}
