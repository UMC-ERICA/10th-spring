package umc.server.domain.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.restaurant.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
