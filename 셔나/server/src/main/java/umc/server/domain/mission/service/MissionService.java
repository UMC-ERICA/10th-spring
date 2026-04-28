package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.global.apiPayload.exception.GeneralException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.GetMissionListDTO getMissionList(Long memberId, MissionStatus missionStatus) {
        List<MemberMission> memberMissionList = memberMissionRepository.findAllByMemberIdAndMissionStatus(memberId, missionStatus);

        return MissionConverter.toGetMissionListDTO(memberMissionList);
    }

    @Transactional
    public MissionResDTO.UpdateMissionStatusResultDTO updateMissionCompleted(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new GeneralException(MissionErrorCode.MISSION_NOT_FOUND));

        memberMission.missionComplete();

        return MissionConverter.toUpdateMissionStatusResultDTO(memberMission);
    }
}
