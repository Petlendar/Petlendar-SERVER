package board.domain.comment.service;

import board.domain.board.service.BoardService;
import board.domain.comment.controller.model.update.CommentUpdateRequest;
import db.domain.board.BoardRepository;
import db.domain.comment.CommentEntity;
import db.domain.comment.CommentRepository;
import db.domain.comment.enums.CommentStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentEntity register(CommentEntity commentEntity, Long userId) {
        commentEntity.setStatus(CommentStatus.REGISTERED);
        commentEntity.setRegisteredAt(LocalDateTime.now());
        commentEntity.setUserId(userId);
        return commentRepository.save(commentEntity);
    }

    public void notExistsByCommentWithThrow(Long commentId, Long userId) {
        Boolean existsByComment = commentRepository.existsByIdAndUserIdAndStatusNot(commentId,
            userId, CommentStatus.UNREGISTERED);
        if (!existsByComment) {
            throw new RuntimeException("댓글이 존재하지 않습니다.");
        }

    }

    public CommentEntity getCommentBy(Long commentId) {
        return commentRepository.findFirstByIdAndStatusNotOrderByIdDesc(commentId,
            CommentStatus.UNREGISTERED).orElseThrow(() -> new RuntimeException("댓글이 존재하지 않습니다."));
    }

    public CommentEntity update(CommentEntity commentEntity, CommentUpdateRequest updateRequest) {
        commentEntity.setContent(updateRequest.getContent());
        commentEntity.setStatus(CommentStatus.MODIFIED);
        commentEntity.setModifiedAt(LocalDateTime.now());
        return commentRepository.save(commentEntity);
    }

    public void unregister(CommentEntity commentEntity) {
        commentEntity.setUnregisteredAt(LocalDateTime.now());
        commentEntity.setStatus(CommentStatus.UNREGISTERED);
        commentRepository.save(commentEntity);
    }

    // EMPTY 가능
    public List<CommentEntity> getCommentListBy(Long boardId) {
        return commentRepository.findAllByBoardIdAndStatusNotOrderByRegisteredAtAsc(
            boardId, CommentStatus.UNREGISTERED);

    }
}
