package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.member.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
}
