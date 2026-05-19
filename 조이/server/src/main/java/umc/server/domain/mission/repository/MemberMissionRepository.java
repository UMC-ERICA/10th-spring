package umc.server.domain.mission.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.Status;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    Page<MemberMission> findByMemberIdAndMissionStatus(Long memberId, Status status, Pageable pageable);

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
}
