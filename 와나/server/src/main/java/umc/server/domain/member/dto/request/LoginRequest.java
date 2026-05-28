package umc.server.domain.member.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
