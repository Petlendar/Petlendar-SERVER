package db.domain.comment;

import db.domain.comment.enums.CommentStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Boolean existsByIdAndUserIdAndStatusNot(Long commentId, Long userId, CommentStatus status);

    Optional<CommentEntity> findFirstByIdAndStatusNotOrderByIdDesc(Long commentId,
        CommentStatus commentStatus);

    List<CommentEntity> findAllByBoardIdAndStatusNotOrderByRegisteredAtAsc(Long boardId, CommentStatus commentStatus);
}
