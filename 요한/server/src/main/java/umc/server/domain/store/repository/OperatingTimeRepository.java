package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.store.entity.OperatingTime;

public interface OperatingTimeRepository extends JpaRepository<OperatingTime, Long> {
}
