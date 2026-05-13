package umc.server.domain.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.server.domain.notification.entity.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    @Query("""
            select case when count(a) > 0 then true else false end
            from Alarm a
            where a.member.id = :memberId
              and a.readAt is null
              and a.deletedAt is null
            """)
    boolean existsUnreadAlarmByMemberId(@Param("memberId") Long memberId);
}
