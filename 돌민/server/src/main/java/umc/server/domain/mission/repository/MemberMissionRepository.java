package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.mission.dto.response.MemberMissionResponse;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MemberMissionStatus;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    @Query("""
            select new umc.server.domain.mission.dto.response.MemberMissionResponse(
                mm.id,
                m.id,
                s.name,
                m.missionContent,
                m.point,
                mm.status,
                mm.acceptedAt,
                mm.completedAt,
                m.deadline
            )
            from MemberMission mm
            join mm.mission m
            join m.store s
            where mm.member.id = :memberId
              and mm.status = :status
              and mm.deletedAt is null
              and m.deletedAt is null
              and s.deletedAt is null
            order by mm.createdAt desc
            """)
    Page<MemberMissionResponse> findMemberMissionsByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status,
            Pageable pageable
    );

    @Query("""
            select count(mm)
            from MemberMission mm
            where mm.member.id = :memberId
              and mm.status = :status
              and mm.deletedAt is null
            """)
    long countByMemberIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("status") MemberMissionStatus status
    );
}
