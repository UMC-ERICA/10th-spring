package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umc.server.domain.mission.entity.Mission;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM Mission m JOIN FETCH m.store s WHERE s.storeRegion = :regionName")
    List<Mission> findAllByRegionName(String regionName);
}
