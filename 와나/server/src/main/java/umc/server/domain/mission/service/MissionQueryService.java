package umc.server.domain.mission.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberMission;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.member.exception.MemberErrorCode;
import umc.server.domain.member.exception.MemberException;
import umc.server.domain.member.repository.MemberMissionRepository;
import umc.server.domain.member.repository.MemberRepository;
import umc.server.domain.mission.dto.GetMissionsCountResponse;
import umc.server.domain.mission.dto.GetMissionsResponse;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.enums.MissionStatus;
import umc.server.domain.mission.exception.MissionErrorCode;
import umc.server.domain.mission.exception.MissionException;
import umc.server.domain.mission.repository.MissionRepository;
import umc.server.global.paging.CursorPageResponse;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    private final int SIZE = 10;

    public CursorPageResponse<GetMissionsResponse> getMyMissions(
            Long memberId,
            MemberMissionStatus status,
            Long cursor
    ) {
        Member member = findMemberById(memberId);

        // cursor가 null이면 처음부터, 아니면 해당 id 이후부터
        // TODO : cursor 기반 페이지네이션 구현
        return null;
    }

    public GetMissionsCountResponse getMyMissionCount(Long memberId, MemberMissionStatus status) {
        // TODO : 추후 구현
        return null;
    }

    public GetMissionsResponse getMissions(MissionStatus missionStatus) {
        // TODO : 추후 구현
        return null;
    }

    private Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));
    }


    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
    }
}
