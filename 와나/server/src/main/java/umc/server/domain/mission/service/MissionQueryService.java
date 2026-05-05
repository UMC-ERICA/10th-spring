package umc.server.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.mission.dto.GetMissionsCountResponse;
import umc.server.domain.mission.dto.GetMissionsResponse;
import umc.server.domain.mission.entity.enums.MissionStatus;

@Service
@RequiredArgsConstructor
public class MissionQueryService {
    public GetMissionsResponse getMyMissions(Long memberId, MemberMissionStatus status) {
        // TODO : 추후 구현
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
}
