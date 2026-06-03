package umc.server.domain.member.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import lombok.*;
import umc.server.domain.inquiry.entity.Inquiry;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.MemberStatus;
import umc.server.domain.member.enums.SocialType;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.notification.entity.Alarm;
import umc.server.domain.notification.entity.AlarmSetting;
import umc.server.domain.review.entity.Review;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@SQLDelete(sql = "UPDATE member SET deleted_at = NOW() WHERE member_id = ?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "point", nullable = false)
    @Builder.Default
    private Integer point = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 20)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    private MemberStatus status = MemberStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", nullable = false, length = 30)
    private SocialType socialType;

    @Column(name = "social_uid", length = 100)
    private String socialUid;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberAddress> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PreferenceFood> preferenceFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MemberTermCondition> memberTermConditions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Inquiry> inquiries = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlarmSetting> alarmSettings = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Alarm> alarms = new ArrayList<>();

    public void addPoint(Integer point) {
        this.point += point;
    }

    public void updateProfile(String phone) {
        this.phone = phone;
    }

    public void addAddress(MemberAddress address) {
        this.addresses.add(address);
    }

    public void addPreferenceFood(PreferenceFood preferenceFood) {
        this.preferenceFoods.add(preferenceFood);
    }

    public void addTermCondition(MemberTermCondition memberTermCondition) {
        this.memberTermConditions.add(memberTermCondition);
    }

    public void addMemberMission(MemberMission memberMission) {
        this.memberMissions.add(memberMission);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addInquiry(Inquiry inquiry) {
        this.inquiries.add(inquiry);
    }

    public void addAlarmSetting(AlarmSetting alarmSetting) {
        this.alarmSettings.add(alarmSetting);
    }

    public void addAlarm(Alarm alarm) {
        this.alarms.add(alarm);
    }
}
