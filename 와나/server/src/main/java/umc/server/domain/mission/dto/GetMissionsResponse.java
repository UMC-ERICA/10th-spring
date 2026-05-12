package umc.server.domain.mission.dto;

import java.time.LocalDate;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.restaurant.entity.enums.FoodCategory;

public record GetMissionsResponse(
        String storeName,
        FoodCategory storeType,
        int point,
        LocalDate deadline
) {

    public static GetMissionsResponse from(Mission mission, FoodCategory category) {
        return new GetMissionsResponse(
                mission.getRestaurant().getName(),
                category,
                mission.getAccPoint(),
                mission.getDeadLine().toLocalDate()
        );
    }
}
