package board.domain.comment.business;

import board.domain.board.service.BoardService;
import board.domain.comment.controller.model.register.CommentRegisterRequest;
import board.domain.comment.controller.model.register.CommentRegisterResponse;
import board.domain.comment.controller.model.update.CommentUpdateRequest;
import board.domain.comment.controller.model.update.CommentUpdateResponse;
import board.domain.comment.converter.CommentConverter;
import board.domain.comment.service.CommentService;
import db.domain.comment.CommentEntity;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class CommentBusiness {

    private final CommentService commentService;
    private final BoardService boardService;
    private final CommentConverter commentConverter;

    public CommentRegisterResponse register(CommentRegisterRequest registerRequest, Long userId) {

        // 게시글이 존재하는지 확인
        boardService.notExistsByBoardWithThrow(registerRequest.getBoardId());

        CommentEntity commentEntity = commentConverter.toEntity(registerRequest);
        CommentEntity savedCommentEntity =  commentService.register(commentEntity, userId);

        return commentConverter.toResponse(savedCommentEntity);
    }

    public CommentUpdateResponse update(CommentUpdateRequest updateRequest, Long userId) {

        // 댓글 존재 유무 확인
        commentService.notExistsByCommentWithThrow(updateRequest.getCommentId(), userId);

        CommentEntity commentEntity = commentService.getCommentBy(updateRequest.getCommentId());
        CommentEntity updatedEntity = commentService.update(commentEntity, updateRequest);

        return commentConverter.toUpdateResponse(updatedEntity);

    }
}
