package umc.server.domain.mission.entity.mapping;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.common.entity.BaseEntity;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.entity.Mission;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="member_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @Column(name="is_completed", nullable=false)
    @Builder.Default
    private Boolean isCompleted =  Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
}
