package umc.server.domain.member.dto.request;

public record MemberAddrRequest(
        String city,
        String sigungu,
        String dongeupmyun,
        String street,
        String building
) {
}
