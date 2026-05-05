package umc.server.domain.member.dto.request;

public record MemberSignUpRequest(
        String name,
        String email,
        String password,
        String phoneNumber,
        String nickname
) {
}