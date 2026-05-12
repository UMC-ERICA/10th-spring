package umc.server.domain.store.converter;

import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.dto.StoreResDTO;
import umc.server.domain.store.entity.Store;

public class StoreConverter {


    public static Store toStore(StoreReqDTO.JoinStore dto) {
        return Store.builder()
                .name(dto.name())
                .category(dto.category())
                .address(dto.address())
                .build();
    }


    public static StoreResDTO.StoreInfo toStoreInfo(Store store) {
        return StoreResDTO.StoreInfo.builder()
                .storeId(store.getId())
                .name(store.getName())
                .category(store.getCategory())
                .rating(store.getRating())
                .build();
    }
}