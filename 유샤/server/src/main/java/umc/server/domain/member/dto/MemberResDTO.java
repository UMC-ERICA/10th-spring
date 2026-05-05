package umc.server.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    @Builder
    public record SignUp(
            Long id
    ){}

    @Builder
    public record GetAddress(
            Long addressId,
            String regionSub
    ){}
}
