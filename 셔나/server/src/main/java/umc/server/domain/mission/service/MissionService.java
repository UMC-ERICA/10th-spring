package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public MissionResDTO.GetMissionListDTO getMissionList(Long memberId, MissionStatus missionStatus, Integer page, Integer size) {
        // '최신순' 정렬을 기본값으로 강제 주입
        Sort defaultSort = Sort.by(Sort.Direction.DESC, "createdAt");

        // 페이지네이션 설정
        PageRequest pageRequest = PageRequest.of(page, size, defaultSort);

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberIdAndMissionStatus(memberId, missionStatus, pageRequest);
        return MissionConverter.toGetMissionListDTO(memberMissionPage);
    }

    @Transactional
    public MissionResDTO.UpdateMissionStatusResultDTO updateMissionCompleted(Long memberId, Long missionId) {
        MemberMission memberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        memberMission.missionComplete();

        return MissionConverter.toUpdateMissionStatusResultDTO(memberMission);
    }

    public MissionResDTO.GetHomeMissionListDTO getHomeMissionList(String regionName) {
        List<Mission> missionList = missionRepository.findAllByRegionName(regionName);

        return MissionConverter.toGetHomeMissionListDTO(missionList);
    }
}
