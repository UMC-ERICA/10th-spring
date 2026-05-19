package umc.server.domain.member.converter;

import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member; // 엔티티 경로 확인 필요

public class MemberConverter {

    public static Member toMember(MemberReqDTO.CreateMember dto, String encodedPassword) {
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .detailAddress(dto.detailAddress())
                .email(dto.email())
                .pw(encodedPassword)
                .build();
    }


    public static MemberResDTO.MemberInfo toMemberInfo(Member member) {
        return MemberResDTO.MemberInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

}