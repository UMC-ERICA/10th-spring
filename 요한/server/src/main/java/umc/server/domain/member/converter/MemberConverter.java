package umc.server.domain.member.converter;

import umc.server.domain.member.dto.MemberResponseDTO;
import umc.server.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResponseDTO.MyPageDTO toMyPageDTO(Member member) {
        return new MemberResponseDTO.MyPageDTO(
                member.getUsername(),
                member.getEmail(),
                member.getCurrentPoint()
        );
    }
}
