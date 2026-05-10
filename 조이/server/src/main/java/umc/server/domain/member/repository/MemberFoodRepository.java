package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.entity.mapping.MemberFood;

@Repository
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}
