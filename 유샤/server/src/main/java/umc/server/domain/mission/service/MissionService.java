package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.common.entity.Address;
import umc.server.domain.common.repository.AddressRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MemberMissionRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final AddressRepository addressRepository;

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
}
