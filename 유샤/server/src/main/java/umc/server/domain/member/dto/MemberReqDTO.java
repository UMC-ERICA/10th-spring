package umc.server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.Provider;

import java.time.LocalDate;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SignUp(
            @NotNull(message = "소셜로그인 제공자는 필수입니다.")
            Provider provider,
            @NotNull(message = "소셜로그인UID는 필수입니다.")
            String socialUid,
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birth
    ){}

    public record GetAddress(
            Long id
    ){}
}
