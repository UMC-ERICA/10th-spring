package umc.server.domain.member.converter;

import org.springframework.data.domain.Page;
import umc.server.domain.member.dto.MemberRequestDTO;
import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.MemberRole;
import umc.server.domain.mission.converter.MissionConverter;
import umc.server.domain.mission.entity.Mission;
import umc.server.global.common.entity.Address;

import java.time.LocalDateTime;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return new MemberResponseDTO.JoinResultDTO(
                member.getId(),
                member.getCreatedAt()
        );
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(String accessToken) {
        return new MemberResponseDTO.LoginResultDTO(
                accessToken,
                LocalDateTime.now()
        );
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request, String encodedPassword, Address address) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .password(encodedPassword)
                .username(request.username())
                .gender(request.gender())
                .birth(request.birth())
                .address(address)
                .role(MemberRole.CUSTOMER) // 기본 역할 설정
                .build();
    }

    public static MemberResponseDTO.MyPageDTO toMyPageDTO(Member member) {
        return new MemberResponseDTO.MyPageDTO(
                member.getUsername(),
                member.getEmail(),
                member.getCurrentPoint()
        );
    }

    public static MemberResponseDTO.HomeDTO toHomeDTO(Member member, Page<Mission> missionPage, Integer missionProgress) {
        return new MemberResponseDTO.HomeDTO(
                member.getAddress().getRegion().getName(),
                missionProgress,
                MissionConverter.toMissionListDTOFromMission(missionPage)
        );
    }
}
