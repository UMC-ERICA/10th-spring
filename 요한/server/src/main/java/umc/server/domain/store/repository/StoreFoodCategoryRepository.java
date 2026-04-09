package umc.server.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.store.entity.mapping.StoreFoodCategory;

public interface StoreFoodCategoryRepository extends JpaRepository<StoreFoodCategory, Long> {
}
