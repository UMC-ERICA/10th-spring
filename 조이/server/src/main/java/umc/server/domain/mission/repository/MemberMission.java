package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMission extends JpaRepository<MemberMission, Long> {
}
