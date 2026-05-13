package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import lombok.*;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.store.entity.Store;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mission")
@SQLDelete(sql = "UPDATE mission SET deleted_at = NOW() WHERE mission_id = ?")
@SQLRestriction("deleted_at IS NULL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "mission_content", nullable = false, columnDefinition = "TEXT")
    private String missionContent;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    private MissionStatus status = MissionStatus.READY;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission")
    @Builder.Default
    private List<MemberMission> memberMissions = new ArrayList<>();

    public void updateStatus(MissionStatus status) {
        this.status = status;
    }

    public void addMemberMission(MemberMission memberMission) {
        this.memberMissions.add(memberMission);
    }
}
