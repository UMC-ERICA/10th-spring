package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    public MissionResDTO.MissionStatusList getMissions(MissionReqDTO.GetMission request) {

        List<MissionResDTO.MissionDetail> list = List.of(
                new MissionResDTO.MissionDetail("스타벅스", "CAFE", "커피 사기 미션", 3)
        );

        return new MissionResDTO.MissionStatusList(list);
    }
}