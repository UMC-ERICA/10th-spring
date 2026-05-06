package umc.server.domain.mission.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import lombok.*;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.enums.MemberMissionStatus;
import umc.server.global.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_mission")
@SQLDelete(sql = "UPDATE member_mission SET deleted_at = NOW() WHERE member_mission_id = ?")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_mission_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    private MemberMissionStatus status = MemberMissionStatus.PROGRESS;

    @Column(name = "accepted_at")
    private LocalDateTime acceptedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void complete() {
        this.status = MemberMissionStatus.COMPLETE;
        this.completedAt = LocalDateTime.now();
    }

    public void accept() {
        this.status = MemberMissionStatus.PROGRESS;
        this.acceptedAt = LocalDateTime.now();
    }

    public void close() {
        this.status = MemberMissionStatus.LOCK;
        this.closedAt = LocalDateTime.now();
    }
}
