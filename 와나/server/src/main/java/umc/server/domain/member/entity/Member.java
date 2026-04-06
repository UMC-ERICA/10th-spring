package umc.server.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.enums.Gender;
import umc.server.domain.member.entity.enums.MemberRole;
import umc.server.domain.member.entity.enums.MemberStatus;
import umc.server.domain.member.entity.enums.LoginType;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private Gender gender;
    private LocalDate birth;
    private Long point;
    private MemberStatus status;
    private MemberRole role;
    private boolean authentificatedPhone;

    // 소셜로그인 관련 필드
    private LoginType socialType;
    private String refreshToken;
    private Long socialId;

}
