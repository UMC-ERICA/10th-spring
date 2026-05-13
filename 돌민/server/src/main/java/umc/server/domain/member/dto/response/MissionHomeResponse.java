package umc.server.domain.member.dto.response;

import umc.server.domain.store.enums.StoreCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record MissionHomeResponse(
        Long missionId,
        Long storeId,
        String storeName,
        String storeCategory,
        String missionContent,
        Integer point,
        LocalDateTime deadline,
        long dDay
) {
    public MissionHomeResponse(
            Long missionId,
            Long storeId,
            String storeName,
            StoreCategory storeCategory,
            String missionContent,
            Integer point,
            LocalDateTime deadline
    ) {
        this(
                missionId,
                storeId,
                storeName,
                storeCategory.name(),
                missionContent,
                point,
                deadline,
                calculateDDay(deadline)
        );
    }

    private static long calculateDDay(LocalDateTime deadline) {
        return ChronoUnit.DAYS.between(LocalDate.now(), deadline.toLocalDate());
    }
}
