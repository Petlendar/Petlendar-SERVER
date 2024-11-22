package board.domain.comment.business;

import board.common.response.MessageConverter;
import board.common.response.MessageResponse;
import board.domain.comment.controller.model.detail.CommentDetailResponse;
import board.domain.board.service.BoardService;
import board.domain.comment.controller.model.register.CommentRegisterRequest;
import board.domain.comment.controller.model.register.CommentRegisterResponse;
import board.domain.comment.controller.model.update.CommentUpdateRequest;
import board.domain.comment.controller.model.update.CommentUpdateResponse;
import board.domain.comment.converter.CommentConverter;
import board.domain.comment.service.CommentService;
import db.domain.comment.CommentEntity;
import global.annotation.Business;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class CommentBusiness {

    private final CommentService commentService;
    private final BoardService boardService;
    private final CommentConverter commentConverter;
    private final MessageConverter messageConverter;

    public CommentRegisterResponse register(CommentRegisterRequest registerRequest, Long userId) {

        // 게시글이 존재하는지 확인
        boardService.notExistsByBoardWithThrow(registerRequest.getBoardId());

        CommentEntity commentEntity = commentConverter.toEntity(registerRequest);
        CommentEntity savedCommentEntity =  commentService.register(commentEntity, userId);

        return commentConverter.toResponse(savedCommentEntity);
    }

    public List<CommentDetailResponse> getCommentDetailListBy(Long boardId) {
        List<CommentEntity> commentEntityList = commentService.getCommentListBy(boardId);
        return commentConverter.toResponse(
            commentEntityList);
    }

    public CommentUpdateResponse update(CommentUpdateRequest updateRequest, Long userId) {

        // 댓글 존재 유무 확인
        commentService.notExistsByCommentWithThrow(updateRequest.getCommentId(), userId);

        CommentEntity commentEntity = commentService.getCommentBy(updateRequest.getCommentId());
        CommentEntity updatedEntity = commentService.update(commentEntity, updateRequest);

        return commentConverter.toUpdateResponse(updatedEntity);

    }

    public MessageResponse unregister(Long commentId, Long userId) {

        // 댓글 존재 유무 확인
        commentService.notExistsByCommentWithThrow(commentId, userId);

        CommentEntity commentEntity = commentService.getCommentBy(commentId);
        commentService.unregister(commentEntity);

        return messageConverter.toResponse("댓글이 삭제되었습니다.");
    }

}
