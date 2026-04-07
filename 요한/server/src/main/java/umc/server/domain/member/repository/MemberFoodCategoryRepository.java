package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.mapping.MemberFoodCategory;

public interface MemberFoodCategoryRepository extends JpaRepository<MemberFoodCategory, Long> {
}
