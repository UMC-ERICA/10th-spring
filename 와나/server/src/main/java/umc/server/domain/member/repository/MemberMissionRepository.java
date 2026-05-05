package umc.server.domain.member.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberMission;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.mission.entity.Mission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findAllByMemberAndStatus(Member member, MemberMissionStatus status);
}
