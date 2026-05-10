package umc.server.domain.mission.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByStoreId(Long storeId);
}
