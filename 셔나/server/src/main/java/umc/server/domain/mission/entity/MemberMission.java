package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.server.domain.alarm.entity.ReviewRequestAlarm;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@DynamicUpdate
@DynamicInsert
@Table(name = "member_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_status")
    @Enumerated(EnumType.STRING)
    private MissionStatus missionStatus;

    @Column(name = "auth_code", length = 10, nullable = false)
    private String authCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "memberMission", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ReviewRequestAlarm> reviewRequestAlarmList = new ArrayList<>();
}
