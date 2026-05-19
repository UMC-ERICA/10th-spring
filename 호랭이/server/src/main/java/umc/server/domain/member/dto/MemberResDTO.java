package umc.server.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record MemberInfo(
            Long memberId,
            String name,
            //String profileurl,
            String email,
            String phoneNumber,
            Integer point
    ){}
}
