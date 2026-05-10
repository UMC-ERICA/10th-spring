package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberMission;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.exception.MissionErrorCode;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public void completeMission(Long memberId, Long missionId) {
        Member member = findMemberById(memberId);
        Mission mission = findMissionById(missionId);

        MemberMission memberMission = memberMissionRepository.findByMemberAndMission(member, mission)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MEMBER_MISSION_NOT_FOUND));

        memberMission.updateStatus(MemberMissionStatus.COMPLETED);
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    private Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));
    }
}
