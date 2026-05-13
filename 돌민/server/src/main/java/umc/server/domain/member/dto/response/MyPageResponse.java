package umc.server.domain.member.dto.response;

public record MyPageResponse(
        Long memberId,
        String name,
        String email,
        String phone,
        boolean phoneVerified,
        Integer point,
        long reviewCount,
        long inquiryCount
) {
}
