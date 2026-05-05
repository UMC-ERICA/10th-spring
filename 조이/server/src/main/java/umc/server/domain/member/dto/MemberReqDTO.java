package umc.server.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import umc.server.domain.member.enums.Gender;

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
