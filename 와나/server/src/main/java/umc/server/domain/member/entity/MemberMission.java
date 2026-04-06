package umc.server.domain.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.mission.entity.Mission;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private MemberMissionStatus status;

    private MemberMission(Member member, Mission mission) {
        this.member = member;
        this.mission = mission;
        this.status = MemberMissionStatus.NOT_STARTED;
    }


    public static MemberMission create(Member member, Mission mission) {
        return new MemberMission(member, mission);
    }

}
