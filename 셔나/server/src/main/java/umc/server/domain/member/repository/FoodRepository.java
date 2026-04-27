package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
