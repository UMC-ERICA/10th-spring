package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.server.domain.alarm.entity.NewMissionAlarm;
import umc.server.domain.store.entity.Store;
import umc.server.global.entity.BaseEntity;

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
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mission_title", length = 100, nullable = false)
    private String missionTitle;

    @Column(name = "mission_description", columnDefinition = "TEXT", nullable = false)
    private String missionDescription;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "reward_points", nullable = false)
    private int rewardPoints;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "mission")
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<NewMissionAlarm> newMissionAlarmList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
