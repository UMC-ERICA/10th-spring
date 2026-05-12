package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.Mission;
import umc.server.global.common.entity.Region;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStoreAddressRegion(Region region, Pageable pageable);
}
