package umc.server.domain.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.common.entity.Address;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.Provider;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SignUp(

            List<Long> termIds,

            @NotNull(message = "소셜로그인 제공자는 필수입니다.")
            Provider provider,
            @NotNull(message = "소셜로그인UID는 필수입니다.")
            String socialUid,
            @NotBlank(message = "이름은 필수입니다.")
            String name,
            @NotNull(message = "성별은 필수입니다.")
            Gender gender,
            @NotNull(message = "생년월일은 필수입니다.")
            LocalDate birth,

            // Address address,

            @NotBlank(message="email은 필수입니다.")
            @Email
            String email,

            @NotBlank(message="password는 필수입니다.")
            String password,

            List<Long> favFoodIds
    ){}

    public record GetAddress(
            Long id
    ){}

    public record Login(
            @Email
            @NotBlank(message = "email을 입력하세요.")
            String email,
            @NotBlank(message = "password를 입력하세요.")
            String password
    ){}
}
