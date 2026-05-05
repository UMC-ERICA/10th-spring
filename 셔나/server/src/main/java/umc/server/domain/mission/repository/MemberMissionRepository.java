package umc.server.domain.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import umc.server.domain.mission.entity.MemberMission;
import umc.server.domain.mission.enums.MissionStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    // 특정 회원의 상태별 미션 목록 조회 (진행중/진행완료)
    @Query("SELECT mm FROM MemberMission mm JOIN FETCH mm.mission WHERE mm.member.id = :memberId AND mm.missionStatus = :status")
    List<MemberMission> findAllByMemberIdAndMissionStatus(Long memberId, MissionStatus status);

    // 특정 회원의 특정 미션 기록 찾기
    @Query("SELECT mm FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.mission.id = :missionId")
    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
}
