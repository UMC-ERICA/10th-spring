package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
}
