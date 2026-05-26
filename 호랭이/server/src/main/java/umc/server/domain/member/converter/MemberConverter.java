package umc.server.domain.member.converter;

import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member; // 엔티티 경로 확인 필요
import umc.server.global.security.DTO.OAuthDTO;

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


    public static MemberResDTO.GetInfo toGetInfo(Member member) {
        return MemberResDTO.GetInfo.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }
}