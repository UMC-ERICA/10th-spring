package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.enums.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.request.MyMissionRequest;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.dto.request.MissionReqDTO;
import umc.server.domain.store.dto.response.MissionResDTO;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.enums.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreRepository;
import umc.server.global.apiPayload.PageResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    /**
     * 특정 가게에 미션을 생성
     */
    @Transactional
    public void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);
        missionRepository.save(mission);
    }

    /**
     * 특정 가게의 미션 목록을 조회
     */
    @Transactional(readOnly = true)
    public List<MissionResDTO.GetMission> getMissions(Long storeId) {
        storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        return missionRepository.findAllByStoreId(storeId);
    }

    /**
     * 내가 진행중인 미션 목록을 오프셋 기반 페이지네이션
     */
    @Transactional(readOnly = true)
    public PageResponse<MemberMissionResponse> getMyMissions(MyMissionRequest request) {
        memberRepository.findActiveById(request.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageable = PageRequest.of(request.page(), request.size());
        Page<MemberMissionResponse> page = memberMissionRepository.findMemberMissionsByMemberIdAndStatus(
                request.memberId(),
                request.status(),
                pageable
        );

        return PageResponse.from(page);
    }
}
