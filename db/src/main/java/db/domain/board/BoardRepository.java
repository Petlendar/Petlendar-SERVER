package db.domain.board;

import db.domain.board.enums.BoardStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    Optional<BoardEntity> findFirstByIdAndStatusNotOrderByIdDesc(Long boardId, BoardStatus status);

    Boolean existsByIdAndUserIdAndStatusNot(Long boardId, Long userId, BoardStatus status);
}
