package umc.server.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.converter.StoreConverter;
import umc.server.domain.store.dto.StoreReqDTO;
import umc.server.domain.store.dto.StoreResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional
    public StoreResDTO.CreateMissionResultDTO createMission(Long storeId, StoreReqDTO.CreateMissionDTO request) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission newMission = StoreConverter.toMission(request);
        newMission.setStore(store);

        Mission savedMission = missionRepository.save(newMission);

        return StoreConverter.toCreateMissionResultDTO(savedMission);
    }
}
