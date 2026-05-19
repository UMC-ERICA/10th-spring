package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.common.entity.Address;
import umc.server.domain.common.repository.AddressRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.store.entity.Store;
import umc.server.domain.store.exception.StoreException;
import umc.server.domain.store.exception.code.StoreErrorCode;
import umc.server.domain.store.repository.StoreRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final AddressRepository addressRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.GetMissionList getMissionList(boolean isCompleted){

        List<Mission> missionList;

        if(isCompleted) {
            missionList = memberMissionRepository.findCompletedMissionList(1L, LocalDateTime.now(),0L);
        }else{
            missionList = memberMissionRepository.findUncompletedMissionList(1L, LocalDate.now(),0L);
        }

        return MissionConverter.toGetMissionListResult(missionList);
    }


    public MissionResDTO.GetProgress getProgress(Long addressId) {

        Address address = addressRepository.findById(addressId).orElseThrow();
        String regionSub = address.getRegionSub();

        Integer progress = memberMissionRepository.findRegionMissionProgress(1L,regionSub);

        return MissionConverter.toGetProgressResult(progress);
    }

    @Transactional
    public Void create(Long storeId, MissionReqDTO.CreateMission dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new StoreException(StoreErrorCode.NOT_FOUND));

        Mission mission = MissionConverter.toMission(store,dto);

        missionRepository.save(mission);
        return null;
    }

    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissionListByStore(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else{
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId,pageRequest);


        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }

    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissionsPage(
            Long memberId,
            Boolean isCompleted,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else{
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionsPage = memberMissionRepository.findMissions(memberId,isCompleted,pageRequest);
        return MissionConverter.toPagination(
                missionsPage.map(MissionConverter::toGetMission).toList(),
                missionsPage.getNumber(),
                missionsPage.getSize()
        );
    }
}
