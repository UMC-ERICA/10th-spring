package umc.server.domain.member.dto;

import umc.server.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberRequestDTO {
    public record JoinDTO(
            String name,
            String email,
            String username,
            Gender gender,
            LocalDate birth,
            Long addressId
    ) {
    }
}
