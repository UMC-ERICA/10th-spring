package umc.server.domain.member.dto;

import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.Provider;

import java.time.LocalDate;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SignUp(
            Provider provider,
            String socialUid,
            String name,
            Gender gender,
            LocalDate birth
    ){}

    public record GetAddress(
            Long id
    ){}
}
