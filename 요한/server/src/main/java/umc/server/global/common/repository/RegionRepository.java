package umc.server.global.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.global.common.entity.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
