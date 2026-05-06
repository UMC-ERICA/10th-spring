package umc.server.domain.mission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.mission.entity.Mission;

@Repository
public interface MemberMission extends JpaRepository<MemberMission, Long> {
    List<Mission> findByMemberId(Long memberId);
}
