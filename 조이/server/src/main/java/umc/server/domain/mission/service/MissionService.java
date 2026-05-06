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
import umc.server.domain.mission.exception.MissionnException;
import umc.server.domain.mission.exception.code.MissionErrorCode;
import umc.server.domain.mission.repository.MemberMission;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.domain.review.converter.ReviewConverter;
import umc.server.domain.review.entity.Review;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMission memberMissionRepository;
    private final MemberRepository memberRepository;

    //미션조회
    public List<MissionResDTO.MissionsResDTO> getMissions() {

        List<Mission> missions = missionRepository.findAll();

        return missions.stream()
                .map(MissionConverter::toMissionsResDTO)
                .collect(Collectors.toList());
    }

    // 내 미션 확인
    public List<MissionResDTO.MissionsResDTO> myMissions(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        List<Mission> missions = memberMissionRepository.findByMemberId(memberId);

        return missions.stream()
                .map(MissionConverter::toMissionsResDTO)
                .collect(Collectors.toList());
    }


}
