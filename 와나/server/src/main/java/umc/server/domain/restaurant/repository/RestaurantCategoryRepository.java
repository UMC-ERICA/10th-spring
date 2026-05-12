package umc.server.domain.restaurant.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.restaurant.entity.Restaurant;
import umc.server.domain.restaurant.entity.RestaurantCategory;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
    Optional<RestaurantCategory> findFirstByRestaurant(Restaurant restaurant);

    @Query("SELECT rc FROM RestaurantCategory rc WHERE rc.restaurant IN :restaurants ORDER BY rc.id ASC")
    List<RestaurantCategory> findByRestaurantIn(@Param("restaurants") List<Restaurant> restaurants);
}
