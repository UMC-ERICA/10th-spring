package umc.server.domain.member.converter;

import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;

public class MemberConverter {

    // entity -> 회원 프로필 조회 DTO
    public static MemberResDTO.GetProfileResultDTO toGetProfileResultDTO(Member member) {
        return MemberResDTO.GetProfileResultDTO.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .totalPoints(member.getTotalPoints())
                .build();
    }
}
