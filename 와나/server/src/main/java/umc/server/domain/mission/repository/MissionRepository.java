package umc.server.domain.mission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.enums.MissionStatus;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findAllByMember(Member member);

    List<Mission> findAllByMemberAndStatus(Member member, MissionStatus status);

    @Query("SELECT m FROM Mission m JOIN FETCH m.restaurant WHERE m.status = :status")
    List<Mission> findAllByStatus(@Param("status") MissionStatus status);
}
