package umc.server.domain.mission.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.member.dto.response.MissionHomeResponse;
import umc.server.domain.mission.entity.Mission;
import umc.server.domain.mission.enums.MissionStatus;
import umc.server.domain.store.dto.response.MissionResDTO;

import java.util.List;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("""
            select m
            from Mission m
            join fetch m.store s
            where m.id = :missionId
              and m.deletedAt is null
              and s.deletedAt is null
            """)
    Optional<Mission> findActiveById(@Param("missionId") Long missionId);

    @Query("""
            select new umc.server.domain.member.dto.response.MissionHomeResponse(
                m.id,
                s.id,
                s.name,
                s.category,
                m.missionContent,
                m.point,
                m.deadline
            )
            from Mission m
            join m.store s
            join s.addresses a
            where a.city = :city
              and a.district = :district
              and a.dong = :dong
              and m.status = :status
              and m.deletedAt is null
              and s.deletedAt is null
              and a.deletedAt is null
              and not exists (
                    select 1
                    from MemberMission mm
                    where mm.member.id = :memberId
                      and mm.mission = m
                      and mm.deletedAt is null
              )
            order by m.deadline asc
            """)
    Page<MissionHomeResponse> findAvailableMissionsByRegion(
            @Param("memberId") Long memberId,
            @Param("city") String city,
            @Param("district") String district,
            @Param("dong") String dong,
            @Param("status") MissionStatus status,
            Pageable pageable
    );

    @Query("""
            select new umc.server.domain.store.dto.response.MissionResDTO.GetMission(
                m.id,
                m.point,
                m.missionContent
            )
            from Mission m
            where m.store.id = :storeId
            order by m.createdAt desc
            """)
    List<MissionResDTO.GetMission> findAllByStoreId(@Param("storeId") Long storeId);
}
