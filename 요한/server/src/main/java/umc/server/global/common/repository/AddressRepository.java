package umc.server.global.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.global.common.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
