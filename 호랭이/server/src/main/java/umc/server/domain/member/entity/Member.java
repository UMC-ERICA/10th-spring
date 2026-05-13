package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.mapping.MemberFood;
import umc.server.domain.member.entity.mapping.MemberTerm;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.SocialType;
import umc.server.domain.mission.enums.Address;
import umc.server.global.apiPayload.code.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Address address;

    @Column(name = "detil_address", nullable = false)
    private String detailAddress;

    @Column(name = "social_uid")
    private String socialUid;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = true)
    private String phoneNumber;

    @Column(name = "profile_url", nullable = false)
    private String profileUrl;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "pw",nullable = false)
    private String pw;
}

