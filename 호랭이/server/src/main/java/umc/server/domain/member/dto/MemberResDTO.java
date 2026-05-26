package umc.server.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            Long memberId,
            String name,
            //String profileurl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    @Builder
    public record Login (
        String accessToken //로그인 응답 데이터 형태
    ){}
}
