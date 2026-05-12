package umc.server.domain.mission.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entitty.Store;
import umc.server.domain.store.exception.Code.StoreErrorCode;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.repository.StoreRepository;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        //미션 생성
        Mission mission = MissionConverter.toMission(store, dto);
        //미션 DB 저장
        missionRepository.save(mission);
    }

    //가게 미션들 조회
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissions(
                Long storeId,
                Integer pageSize,
                Integer pageNumber,
                String sort

        ){
            //정렬 정보
            Sort sortInfo;
            if (sort != null){
                sortInfo = Sort.by(sort);
            }else {
                sortInfo = Sort.by("id").descending();
            }

            // 페이지 정보 PageRequest로
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

                     //가게 내 미션들 조회
            Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

            //미션 응답 DTO로 포장
            return MissionConverter.toPagination(
                    missionList.map(MissionConverter::toGetMission).toList(),
                    missionList.getNumber(),
                    missionList.getSize()
                    );
        }

    public MissionResDTO.Pagination<MissionResDTO.GetMyMission> getMyMissions(
            Long userId, Integer pageSize, Integer pageNumber, String status, String sort) {
        //삼항 연산자 버전
        Sort sortInfo = (sort != null) ? Sort.by(sort) : Sort.by("id").descending();

        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize,sortInfo);

        MissionStatus missionStatus = MissionStatus.valueOf(status.toUpperCase());
        //내 미션 조회
        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndStatus(userId, missionStatus, pageRequest);

        return MissionConverter.toPagination(
                memberMissions.map(MissionConverter::toGetMyMission).toList(),
                memberMissions.getNumber(),
                memberMissions.getSize()
        );

    }
}