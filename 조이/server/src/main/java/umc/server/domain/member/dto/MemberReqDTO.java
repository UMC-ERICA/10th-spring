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

            @NotBlank(message = "이름은 빈칸일 수 없습니다.")
            String userName,
            Gender gender,
            @NotBlank
            String password,

            @Email @NotBlank
            String email,
            @NotBlank(message = "전화번호는 빈칸일 수 없습니다.")
            String tel,
            String address
    ) {
    }

    public record LoginReqDTO(
            @Email @NotBlank
            String email,
            @NotBlank
            String password
    ) {
    }

}
