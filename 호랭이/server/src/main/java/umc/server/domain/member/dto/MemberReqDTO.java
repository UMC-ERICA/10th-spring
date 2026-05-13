package umc.server.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.server.domain.mission.enums.Address;

import java.time.LocalDate;

public class MemberReqDTO {

    public record CreateMember(
        @NotBlank(message = "이름은 필수입니다.")
        String name,
        @NotNull(message = "생년월일은 필수입니다")
        LocalDate birth,
        @NotNull(message = "주소는 필수입니다.")
        Address address,
        @NotBlank(message = "상세주소는 필수입니다.")
        String detailAddress,
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 아닙니다.") //이메일 주소 형식인지 확인
        String email,
        @NotBlank(message = "비밀번호는 필수입니다.")
        String pw

    ){}

    public record  GetInfo(
            Long id
    ){}
}
