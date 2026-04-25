package umc.server.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.server.domain.alarm.entity.Alarm;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.MemberStatus;
import umc.server.domain.member.enums.SocialType;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.review.entity.Review;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "nickname", length = 20)
    private String nickname;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address_zipcode", length = 10, nullable = false)
    private String addressZipcode;

    @Column(name = "address_details", length = 100, nullable = false)
    private String addressDetails;

    @Column(name = "location_name", length = 30, nullable = false)
    private String locationName;

    @Column(name = "total_points")
    @Builder.Default
    private int totalPoints = 0;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "social_type")
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "social_uid", length = 100)
    private String socialUid;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "member_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private MemberStatus memberStatus = MemberStatus.ACTIVE;

    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private TermsAgreed termsAgreed;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alarm> alarmList = new ArrayList<>();

    // 연관관계 편의 메서드
    public void setTermsAgreed(TermsAgreed termsAgreed) {
        this.termsAgreed = termsAgreed;
        if (termsAgreed != null) {
            termsAgreed.setMember(this);
        }
    }
}
