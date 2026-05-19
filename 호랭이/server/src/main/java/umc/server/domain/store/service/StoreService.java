package umc.server.domain.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.entitty.Store;
import umc.server.domain.store.repository.StoreRepository;

import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {
    private final StoreRepository storeRepository;

    public Store createStore(StoreReqDTO.JoinStore dto) {
        Store store = Store.builder()
                .name(dto.name())
                .category(Locale.Category.valueOf(dto.category()))
                .build();
        return storeRepository.save(store);
    }
}
