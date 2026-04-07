package umc.server.domain.mission.entity.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.global.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private MissionStatus status = MissionStatus.CHALLENGING;

    @Column(nullable = false, length = 10)
    private String verificationCode;
}
