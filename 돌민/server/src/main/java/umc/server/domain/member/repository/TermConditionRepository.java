package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.TermCondition;

public interface TermConditionRepository extends JpaRepository<TermCondition, Long> {
}
