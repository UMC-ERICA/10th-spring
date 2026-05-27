package umc.server.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {

    @Builder
    public record StoreInfo(
            Long storeId,
            String name,
            String category, //enum 타입이지만, String 으로 변환이 깔끔
            Float rating
    ) {}
}