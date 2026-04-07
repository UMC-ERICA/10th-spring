package umc.server.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.member.entity.NotificationAllowList;

public interface NotificationAllowListRepository extends JpaRepository<NotificationAllowList, Long> {
}
