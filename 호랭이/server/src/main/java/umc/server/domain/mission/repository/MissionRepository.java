package umc.server.domain.mission.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.Mission;
import java.util.List;
public interface MissionRepository extends JpaRepository<Mission, Long>{
  List<Mission> findAllByStore_Id(Long storeId);
}
