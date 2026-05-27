package umc.server.domain.term.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.term.entity.Terms;

public interface TermRepository extends JpaRepository<Terms, Long> {
}
