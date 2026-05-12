package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.entity.mapping.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;

public interface MemberMissionRepository extends JpaRepository<Mission,Long> {


    @Query("""
        select mm FROM MemberMission mm
        JOIN mm.mission m
        JOIN m.store s
        WHERE mm.member.id = :memberId
        AND mm.status = :status
   """)
    Page<MemberMission> findByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MissionStatus status,
            Pageable pageable);


}
