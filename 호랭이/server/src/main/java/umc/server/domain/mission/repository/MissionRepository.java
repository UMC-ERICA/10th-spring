package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;

import java.util.List;

public interface MissionRepository extends JpaRepository<
        Mission, Long> {
    List<Mission> findByStatus(MissionStatus status);
}