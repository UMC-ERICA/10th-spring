package umc.server.domain.member.dto;

public class MemberResDTO {
    public record JoinResDTO(
            Long memberId,
            String name
    ) {
    }
}
