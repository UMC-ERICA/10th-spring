package umc.server.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import umc.server.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record JoinDTO(
            @Valid
            TermsDTO terms,

            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            @Schema(description = "이메일", example = "test@example.com")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
            @Schema(description = "비밀번호", example = "password123!")
            String password,

            @NotBlank(message = "사용자 이름은 필수입니다.")
            @Schema(description = "사용자 이름", example = "홍길동")
            String username,

            @NotNull(message = "성별을 선택해주세요.")
            @Schema(description = "성별 (MALE, FEMALE, NONE)", example = "FEMALE")
            Gender gender,

            @PastOrPresent(message = "생년월일은 과거 또는 오늘 날짜여야 합니다.")
            @NotNull(message = "생년월일은 필수 입력입니다.")
            @Schema(description = "생년월일 (yyyy-MM-dd)", example = "2000-01-01")
            LocalDate birth,

            @NotBlank(message = "우편번호는 필수입니다.")
            @Schema(description = "우편번호", example = "12345")
            String addressZipcode,

            @NotBlank(message = "지역명은 필수입니다.")
            @Schema(description = "지역명", example = "서울시 강남구")
            String locationName,

            @NotBlank(message = "상세 주소는 필수입니다.")
            @Schema(description = "상세 주소", example = "123동 1234호")
            String addressDetails,

            @Schema(description = "선호 음식 카테고리 ID 리스트", example = "[1, 3, 4]")
            List<Long> memberPreferFoodIds
    ) {}

    public record TermsDTO(
            @AssertTrue(message = "만 14세 이상 동의 여부를 확인해주세요.")
            @Schema(description = "만 14세 이상 동의 여부")
            boolean ageOver14,

            @AssertTrue(message = "서비스 이용약관 동의 여부를 확인해주세요.")
            @Schema(description = "서비스 이용약관 동의 여부")
            boolean serviceTerms,

            @AssertTrue(message = "개인정보 처리방침 동의 여부를 확인해주세요.")
            @Schema(description = "개인정보 처리방침 동의 여부")
            boolean privacyTerms,

            @AssertTrue(message = "위치정보 제공 동의 여부를 선택해주세요.")
            @Schema(description = "위치정보 제공 동의 여부")
            boolean locationTerms,

            @NotNull(message = "마케팅 수신 동의 여부를 선택해주세요.")
            @Schema(description = "마케팅 수신 동의 여부")
            Boolean marketingTerms
    ) {}

    public record LoginDTO(
            @NotBlank(message = "이메일은 필수입니다.")
            @Email(message = "올바른 이메일 형식이어야 합니다.")
            @Schema(description = "이메일", example = "test@example.com")
            String email,

            @NotBlank(message = "비밀번호는 필수입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
            @Schema(description = "비밀번호", example = "password123!")
            String password
    ) {}
}
