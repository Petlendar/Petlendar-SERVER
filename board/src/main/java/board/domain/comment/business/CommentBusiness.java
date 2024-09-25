package board.domain.comment.business;

import board.domain.board.service.BoardService;
import board.domain.comment.controller.model.CommentRegisterRequest;
import board.domain.comment.controller.model.CommentRegisterResponse;
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

}
