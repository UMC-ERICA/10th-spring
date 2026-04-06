package umc.server.domain.restaurant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.restaurant.entity.enums.FoodCategory;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private FoodCategory category;

    private RestaurantCategory(Restaurant restaurant, FoodCategory category) {
        this.restaurant = restaurant;
        this.category = category;
    }

    public static RestaurantCategory create(Restaurant restaurant, FoodCategory category) {
        return new RestaurantCategory(restaurant, category);
    }
}
