package umc.server.domain.member.dto;

import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import umc.server.domain.member.enums.Gender;

@Getter
@Setter
@Builder
public class MemberReqDTO {
    public record JoinReqDTO(

            @NotBlank
            String name,
            Gender gender,

            @Email
            String email,
            String tel,
            String address
    ) {
    }
}
