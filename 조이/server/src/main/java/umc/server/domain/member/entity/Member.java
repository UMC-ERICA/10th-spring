package umc.server.domain.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.security.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.mapping.MemberFood;
import umc.server.domain.member.enums.Gender;
import umc.server.domain.member.enums.IsActive;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.review.entity.Review;
import umc.server.global.entity.BaseEntity;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Column(name = "mission_num")
    private Integer missionNum;

    @Column(name = "total_point")
    private Integer totalPoint;

    @Column(name = "is_active")
    @Enumerated(EnumType.STRING)
    private IsActive isActive;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoods;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissions;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews;

}
