package umc.server.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import umc.server.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberRequestDTO {
    public record JoinDTO(
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "이메일 형식이 올바르지 않습니다.")
            String email,
            @NotBlank(message = "닉네임은 필수입니다.")
            String username,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생년월일은 필수입니다.")
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,
            @NotNull(message = "주소 ID는 필수입니다.")
            Long addressId
    ) {
    }
}
