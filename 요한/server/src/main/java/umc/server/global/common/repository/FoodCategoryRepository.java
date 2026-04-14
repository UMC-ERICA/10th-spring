package umc.server.global.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.global.common.entity.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
