package umc.server.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record StoreInfo(
            Long storeId,
            String name,
            String category,
            Float rating
    ) {}
}