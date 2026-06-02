package umc.server.domain.member.converter;

import umc.server.domain.common.entity.Address;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.enums.Provider;
import umc.server.global.security.dto.OAuthDTO;

import java.util.ArrayList;
import java.util.UUID;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfoResult(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.GetAddress toGetAddressResult(
            Address address
    ){
        return MemberResDTO.GetAddress.builder()
                .addressId(address.getId())
                .regionSub(address.getRegionSub())
                .build();
    }

    // 일반 회원가입용
    public static Member toMember(MemberReqDTO.SignUp dto, String encodePassword) {

        return Member.builder()
                .email(dto.email())
                .provider(Provider.LOCAL)
                .socialUid(dto.email())
                .password(encodePassword)
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                // .address(dto.address())
                .favFoodList(new ArrayList<>())
                .build();
    }

    // 소셜 로그인용
    public static Member toMember(OAuthDTO dto){
        return Member.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .provider(dto.getProvider())
                .socialUid(dto.getSocialUid())
                .password(UUID.randomUUID().toString())
                .build();
    }

    public static MemberResDTO.SignUp toSignUpResultDTO(Member savedMember,String accessToken) {
        return MemberResDTO.SignUp.builder()
                .id(savedMember.getId())
                .accessToken(accessToken)
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }
}
