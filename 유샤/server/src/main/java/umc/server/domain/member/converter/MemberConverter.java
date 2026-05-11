package umc.server.domain.member.converter;

import umc.server.domain.common.entity.Address;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;

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
}
