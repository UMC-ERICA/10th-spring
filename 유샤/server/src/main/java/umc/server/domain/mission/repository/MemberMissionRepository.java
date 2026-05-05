package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.common.entity.Address;
import umc.server.domain.mission.entity.Mission;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MemberMissionRepository extends JpaRepository<Mission, Long> {

    @Query("SELECT m FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isCompleted = true " +
            "AND (mm.updatedAt < :lastUpdatedAt OR (mm.updatedAt = :lastUpdatedAt AND mm.mission.id < :lastMissionId)) " +
            "ORDER BY mm.updatedAt DESC, mm.mission.id DESC")
    List<Mission> findCompletedMissionList(
            @Param("memberId") Long memberId,
            @Param("lastUpdatedAt") LocalDateTime lastUpdatedAt,
            @Param("lastMissionId") Long lastMissionId
    );

    @Query("SELECT m FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "WHERE mm.member.id = :memberId " +
            "AND mm.isCompleted = false " +
            "AND (m.deadline > :deadline OR (m.deadline = :deadline AND mm.mission.id > :lastMissionId)) " +
            "ORDER BY m.deadline ASC, mm.mission.id ASC")
    List<Mission> findUncompletedMissionList(
            @Param("memberId") Long memberId,
            @Param("deadline") LocalDate deadline,
            @Param("lastMissionId") Long lastMissionId
    );

    @Query("SELECT MOD(COUNT(mm), 10) " +
            "FROM MemberMission mm " +
            "JOIN mm.mission m " +
            "JOIN m.store s " +
            "JOIN s.address a "+
            "WHERE mm.member.id = :memberId "+
            "AND a.regionSub = :regionSub " +
            "AND mm.isCompleted = true ")
    Integer findRegionMissionProgress(
            @Param("memberId") Long memberId,
            @Param("regionSub") String regionSub);

}
