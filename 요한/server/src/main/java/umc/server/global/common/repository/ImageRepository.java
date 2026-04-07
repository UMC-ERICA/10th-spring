package umc.server.global.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.server.global.common.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
