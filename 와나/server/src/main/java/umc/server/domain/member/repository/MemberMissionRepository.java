package umc.server.domain.member.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.entity.Member;
import umc.server.domain.member.entity.MemberMission;
import umc.server.domain.member.entity.enums.MemberMissionStatus;
import umc.server.domain.mission.entity.Mission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findAllByMemberAndStatus(Member member, MemberMissionStatus status);

    int countByMember(Member member);

    int countByMemberAndStatus(Member member, MemberMissionStatus status);

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);

    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission m JOIN FETCH m.restaurant WHERE mm.member = :member AND mm.status = :status AND (:cursor IS NULL OR mm.id > :cursor) ORDER BY mm.id ASC")
    List<MemberMission> findByMemberAndStatusWithCursor(
            @Param("member") Member member,
            @Param("status") MemberMissionStatus status,
            @Param("cursor") Long cursor,
            Pageable pageable
    );
}
