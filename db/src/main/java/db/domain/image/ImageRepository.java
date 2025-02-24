package db.domain.image;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    Optional<ImageEntity> findFirstByIdAndUserId(Long imageId, Long userId);

    boolean existsByIdAndUserId(Long imageId, Long userId);

    Optional<ImageEntity> findFirstByPetIdOrderByIdDesc(Long petId);

    List<ImageEntity> findAllByBoardIdOrderByIdAsc(Long boardId);

    List<ImageEntity> findAllByBoardIdIn(List<Long> boardIdList);

}
