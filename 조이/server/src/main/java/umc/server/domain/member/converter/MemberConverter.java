package umc.server.domain.member.converter;

import lombok.Builder;
import umc.server.domain.member.dto.MemberReqDTO;
import umc.server.domain.member.dto.MemberResDTO;
import umc.server.domain.member.entity.Member;

@Builder
public class MemberConverter {
    //dto->엔티티 (회원가입)
    public static Member toMemberEntity(MemberReqDTO.JoinReqDTO request) {
        return Member.builder()
                .name(request.name())
                .email(request.email())
                .gender(request.gender())
                .tel(request.tel())
                .address(request.address())
                .build();
    }

    //엔티티->dto (회원가입)
    public static MemberResDTO.JoinResDTO toMemberDTO(Member member) {
        return MemberResDTO.JoinResDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .build();
    }

    //정보가져오기
    public static MemberResDTO.MyPageResDTO toMyPageDTO(Member member) {
        return MemberResDTO.MyPageResDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .tel(member.getTel())
                .totalPoint(member.getTotalPoint())
                .build();
    }


}
