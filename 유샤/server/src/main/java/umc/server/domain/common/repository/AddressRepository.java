package umc.server.domain.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.domain.common.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
