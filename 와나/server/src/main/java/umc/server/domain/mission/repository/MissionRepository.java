package umc.server.domain.mission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.enums.MissionStatus;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByMember(Member member);

    List<Mission> findAllByMemberAndStatus(Member member, MissionStatus status);

    List<Mission> findAllByStatus(MissionStatus status);
}
