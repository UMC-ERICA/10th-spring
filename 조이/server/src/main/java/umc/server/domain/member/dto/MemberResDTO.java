package umc.server.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberResDTO {
    @Builder
    public record JoinResDTO(
            Long memberId,
            String userName
    ) {
    }

    @Builder
    public record MyPageResDTO(
            Long memberId,
            String userName,
            String email,
            String tel,
            Integer totalPoint
    ) {
    }

    @Builder
    public record LoginResDTO(
            String accessToken
    ) {
    }

}
