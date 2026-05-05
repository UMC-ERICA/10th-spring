package umc.server.domain.mission.dto;

import java.time.LocalDate;
import umc.server.domain.restaurant.entity.enums.FoodCategory;

public record GetMissionsResponse(
        String storeName,
        FoodCategory storeType,
        int point,
        LocalDate deadline
) {
}
