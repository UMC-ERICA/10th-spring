package umc.server.domain.member.dto;

import java.time.LocalDateTime;

public class MemberResponseDTO {
    public record JoinResultDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {
    }
}
