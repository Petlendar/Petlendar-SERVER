package db.domain.image;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findFirstByIdAndUserId(Long imageId, Long userId);

}
