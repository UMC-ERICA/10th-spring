package umc.server.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record GetProfileResultDTO(
            String nickname,
            String email,
            String phoneNumber,
            int totalPoints
    ) {}
}
