package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.common.entity.Address;
import umc.server.domain.common.entity.BaseEntity;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.Provider;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="gender",nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name="birth",nullable = false)
    private LocalDate birth;

    @Column(name="social_uid",nullable = false)
    private String socialUid;

    @Column(name="social_provider",nullable = false)
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="profile_url")
    private String profileUrl;

    @Column(name="point",nullable = false)
    @Builder.Default
    private Integer point = 0;

    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(mappedBy = "member",cascade = CascadeType.REMOVE)
    private List<FavFood> favFoodList;

}
