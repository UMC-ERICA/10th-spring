package umc.server.domain.restaurant.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.restaurant.entity.Restaurant;
import umc.server.domain.restaurant.entity.RestaurantCategory;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
    Optional<RestaurantCategory> findFirstByRestaurant(Restaurant restaurant);
}
