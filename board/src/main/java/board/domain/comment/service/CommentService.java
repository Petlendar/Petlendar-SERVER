package board.domain.comment.service;

import board.common.exception.comment.CommentNotFoundException;
import board.domain.comment.controller.model.update.CommentUpdateRequest;
import db.domain.comment.CommentEntity;
import db.domain.comment.CommentRepository;
import db.domain.comment.enums.CommentStatus;
import global.errorcode.CommentErrorCode;
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
            throw new CommentNotFoundException(CommentErrorCode.COMMENT_NOT_FOUND);
        }

    }

    public CommentEntity getCommentBy(Long commentId) {
        return commentRepository.findFirstByIdAndStatusNotOrderByIdDesc(commentId, CommentStatus.UNREGISTERED)
            .orElseThrow(() ->
                new CommentNotFoundException(CommentErrorCode.COMMENT_NOT_FOUND)
            );
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

    public List<CommentEntity> getCommentListBy(Long boardId) {
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardIdAndStatusNotOrderByRegisteredAtAsc(
            boardId, CommentStatus.UNREGISTERED);

        if (commentEntityList.isEmpty()) {
            throw new CommentNotFoundException(CommentErrorCode.COMMENT_NOT_FOUND);
        }

        return commentEntityList;
    }
}
