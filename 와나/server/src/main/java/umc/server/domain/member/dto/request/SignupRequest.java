package umc.server.domain.member.dto.request;

import java.time.LocalDate;
import umc.server.domain.member.entity.MemberAddr;
import umc.server.domain.member.entity.enums.Gender;

public record SignupRequest(
        String memberName,
        String email,
        Gender gender,
        LocalDate birthDate,
        MemberAddr memberAddr
) {
}
