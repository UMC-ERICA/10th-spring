package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.store.entitty.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
