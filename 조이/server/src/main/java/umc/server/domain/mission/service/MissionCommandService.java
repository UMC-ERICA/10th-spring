package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionReqDTO.CreateMission;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.Status;
import umc.server.domain.mission.exception.MissionnException;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public Void createMission(Long storeId, CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);
        return null;
    }

    public MissionResDTO.MissionsResDTO completeMission(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionnException(MissionErrorCode.MISSION_NOT_FOUND));

        if (memberMission.getStatus() == Status.COMPLETED) {
            throw new MissionnException(MissionErrorCode.MISSION_ALREADY_COMPLETED);
        }

        memberMission.complete();
        return MissionConverter.toMissionsResDTO(memberMission.getMission(), memberMission.getStatus());


    }
}

