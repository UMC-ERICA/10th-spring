package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionReqDTO;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.repository.MissionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    //미션 목록 조회 기능
    public List<MissionResDTO.MissionDetail> getMissions(MissionStatus status) {
        return missionRepository.findByStatus(status)
                .stream()
                .map(MissionConverter::toMissionDetail)
                .collect(Collectors.toList());
    }
}