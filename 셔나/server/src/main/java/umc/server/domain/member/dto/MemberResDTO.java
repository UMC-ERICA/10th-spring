package umc.server.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetProfileResultDTO(
            String nickname,
            String email,
            String phoneNumber,
            int totalPoints
    ) {}
}
