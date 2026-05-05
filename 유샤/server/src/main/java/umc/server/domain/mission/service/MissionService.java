package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.global.apiPayload.ApiResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResDTO.GetMissionList getMissionList(boolean isCompleted){

        List<Mission> missionList;

        if(isCompleted) {
            missionList = missionRepository.findCompletedMissionList(1L, LocalDateTime.now(),0L);
        }else{
            missionList = missionRepository.findUncompletedMissionList(1L, LocalDate.now(),0L);
        }

        return MissionConverter.toGetMissionListResult(missionList);
    }

}
