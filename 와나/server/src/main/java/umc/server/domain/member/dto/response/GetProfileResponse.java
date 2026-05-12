package umc.server.domain.member.dto.response;

import umc.server.domain.member.entity.Member;

public record GetProfileResponse(
            String nickname,
            String email,
            String profileImageUrl,
            boolean phoneVerified,
            long point
) {
    public static GetProfileResponse from(Member member) {
        return new GetProfileResponse(
                member.getName(),
                member.getEmail(),
                member.getProfileImageUrl(),
                member.isAuthentificatedPhone(),
                member.getPoint()
        );
    }
}
