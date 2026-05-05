package umc.server.domain.member.dto.response;

public record MemberLocationResponse(
        Long memberId,
        String city,
        String district,
        String dong,
        String detailAddress
) {
}