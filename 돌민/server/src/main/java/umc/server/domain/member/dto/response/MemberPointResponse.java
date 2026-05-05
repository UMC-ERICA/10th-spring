package umc.server.domain.member.dto.response;

public record MemberPointResponse(
        Long memberId,
        Integer point
) {
}