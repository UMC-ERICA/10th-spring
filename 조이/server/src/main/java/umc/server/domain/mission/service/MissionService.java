package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionReqDTO.CreateMission;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.Status;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    //진행여부에 따른 미션조회
    public MissionResDTO.Pagination<MissionResDTO.MissionsResDTO> getMyMission(
            Long memberId,
            Integer pageSize,
            Integer pageNumber,
            String sort,
            Status status
    ) {
        Sort sortInfo = (sort != null) ? Sort.by(sort) : Sort.by("id").descending();

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MemberMission> memberMissions =
                memberMissionRepository.findByMemberIdAndMissionStatus(memberId, status, pageRequest);

        return MissionConverter.toPagination(
                memberMissions.map(mm -> MissionConverter.toMissionsResDTO(mm.getMission())).toList(),
                memberMissions.getNumber(),
                memberMissions.getSize()
        );
    }


    // 가게 미션 조회 (페이지네이션)
    public MissionResDTO.Pagination<MissionResDTO.MissionsResDTO> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionsList = missionRepository.findByStoreId(storeId, pageRequest);

        return MissionConverter.toPagination(
                missionsList.map(MissionConverter::toMissionsResDTO).toList(),
                missionsList.getNumber(),
                missionsList.getSize()
        );
    }


    public Void createMission(Long storeId, CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);
        return null;
    }
}


