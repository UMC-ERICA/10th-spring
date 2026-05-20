package umc.server.domain.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.dto.StoreResDTO;
import umc.server.domain.store.entitty.Store;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPayload.code.GeneralErrorCode;
import umc.server.global.apiPayload.exception.ProjectException;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreResDTO.StoreInfo createStore(StoreReqDTO.JoinStore dto) {
        Store store = Store.builder()
                .name(dto.name())
                .category(Locale.Category.valueOf(dto.category()))
                .build();
        Store saved = storeRepository.save(store);

        return StoreResDTO.StoreInfo.builder()
                .storeId(saved.getId())
                .name(saved.getName())
                .category(saved.getCategory().name())//카테고리 Enum, .name() 기본 메서드
                .rating(0.0f) //0.0기본값, 가계의 평점은 따로 입력받지 않으므로
                .build();
    }

    public StoreResDTO.StoreInfo getStore(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new ProjectException(GeneralErrorCode.NOT_FOUND));

        return StoreResDTO.StoreInfo.builder()
                .storeId(store.getId())
                .name(store.getName())
                .category(store.getCategory().name())
                .rating(0.0f)
                .build();

    }
}
