package umc.server.domain.member.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.entity.Mission;

public class MemberConverter {

    public static MemberResponseDTO.MyPageDTO toMyPageDTO(Member member) {
        return new MemberResponseDTO.MyPageDTO(
                member.getUsername(),
                member.getEmail(),
                member.getCurrentPoint()
        );
    }

    public static MemberResponseDTO.HomeDTO toHomeDTO(Member member, Page<Mission> missionPage) {
        return new MemberResponseDTO.HomeDTO(
                member.getAddress().getRegion().getName(),
                member.getCompletedMissionCount() % 10,
                MissionConverter.toMissionListDTOFromMission(missionPage)
        );
    }
}
