package umc.server.domain.member.dto.request;

import jakarta.validation.constraints.*;
import umc.server.domain.member.enums.FoodCategory;
import umc.server.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public record MemberSignUpRequest(

        @NotEmpty(message = "약관 동의 목록은 필수입니다.")
        List<TermAgreement> termAgreements,

        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        String password,

        @NotBlank(message = "전화번호는 필수입니다.")
        String phone,

        @NotNull(message = "성별은 필수입니다.")
        Gender gender,

        @NotNull(message = "생년월일은 필수입니다.")
        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        LocalDate birth,

        @NotBlank(message = "시/도는 필수입니다.")
        String city,

        @NotBlank(message = "구/군은 필수입니다.")
        String district,

        @NotBlank(message = "동은 필수입니다.")
        String dong,

        @NotBlank(message = "상세 주소는 필수입니다.")
        String detailAddress,

        @NotEmpty(message = "선호 음식은 최소 1개 이상 선택해야 합니다.")
        List<FoodCategory> preferredFoods

) {

    public record TermAgreement(
            @NotNull(message = "약관 ID는 필수입니다.")
            Long termConditionId,

            @NotNull(message = "약관 동의 여부는 필수입니다.")
            Boolean isAgreed
    ) {}
}
