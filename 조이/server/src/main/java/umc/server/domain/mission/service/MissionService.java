package umc.server.domain.mission.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.exception.code.MemberErrorCode;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.dto.MissionResDTO;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.repository.MemberMissionRepository;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    //미션조회
    public List<MissionResDTO.MissionsResDTO> getMissions(Long memberId) {

        List<MemberMission> memberMissions = memberMissionRepository.findByMemberId(memberId);

        return memberMissions.stream()
                .map(MemberMission::getMission) // MemberMission에서 Mission 엔티티 추출
                .map(MissionConverter::toMissionsResDTO)
                .collect(Collectors.toList());
    }

    // 내 미션 확인
    public List<MissionResDTO.MissionsResDTO> myMissions(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        List<MemberMission> missions = memberMissionRepository.findByMemberId(memberId);

        return missions.stream()
                .map(MemberMission::getMission) // MemberMission에서 Mission 엔티티 추출
                .map(MissionConverter::toMissionsResDTO)
                .toList();
    }

    // 가게 미션 확인
    public List<MissionResDTO.MissionsResDTO> storeMissions(Long storeId) {
        List<Mission> missions = missionRepository.findByStoreId(storeId);

        return missions.stream()
                .map(MissionConverter::toMissionsResDTO)
                .toList();
    }

}
