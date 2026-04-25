package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.MemberRole;
import umc.server.domain.member.enums.SocialProvider;
import umc.server.global.common.BaseEntity;
import umc.server.global.common.entity.Address;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    @Builder.Default
    private SocialProvider socialProvider = SocialProvider.LOCAL;

    @Column(length = 255)
    private String socialUid;

    @Column(nullable = false, length = 10)
    private String username;

    @Column(nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MemberRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentPoint = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer completedMissionCount = 0;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_allow_list_id", nullable = false)
    private NotificationAllowList notificationAllowList;

    private LocalDateTime deletedAt;
}
