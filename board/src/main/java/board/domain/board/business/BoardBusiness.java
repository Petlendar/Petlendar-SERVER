package board.domain.board.business;

import board.common.response.MessageConverter;
import board.common.response.MessageResponse;
import board.domain.board.controller.model.detail.BoardDetailResponse;
import board.domain.board.controller.model.detail.CommentDetailResponse;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.domain.board.converter.BoardConverter;
import board.domain.board.service.BoardService;
import board.domain.comment.converter.CommentConverter;
import board.domain.comment.service.CommentService;
import db.domain.board.BoardEntity;
import db.domain.comment.CommentEntity;
import global.annotation.Business;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class BoardBusiness {

    private final BoardService boardService;
    private final CommentService commentService;
    private final BoardConverter boardConverter;
    private final CommentConverter commentConverter;
    private final MessageConverter messageConverter;

    public BoardRegisterResponse register(BoardRegisterRequest request, Long userId) {

        BoardEntity boardEntity = boardConverter.toEntity(request);
        BoardEntity savedBoardEntity = boardService.register(boardEntity, userId);

        return boardConverter.toResponse(savedBoardEntity);
    }

    public MessageResponse unregister(Long boardId, Long userId) {

        // 게시글 존재 유무 확인
        boardService.notExistsByBoardWithThrow(boardId, userId);

        BoardEntity boardEntity = boardService.getBoardBy(boardId);
        boardService.unregister(boardEntity);

        return messageConverter.toResponse("게시글이 삭제되었습니다.");
    }

    public BoardUpdateResponse update(BoardUpdateRequest updateRequest, Long userId) {

        // 게시글 존재 유무 확인
        boardService.notExistsByBoardWithThrow(updateRequest.getBoardId(), userId);

        BoardEntity boardEntity = boardService.getBoardBy(updateRequest.getBoardId());
        BoardEntity updatedEntity = boardService.update(boardEntity, updateRequest);

        return boardConverter.toUpdateResponse(updatedEntity);
    }

    public BoardDetailResponse getBoardDetailBy(Long boardId) {

        // 게시글 존재 유무 확인
        boardService.notExistsByBoardWithThrow(boardId);

        // 게시글 Entity 조회
        BoardEntity boardEntity = boardService.getBoardBy(boardId);

        // 해당하는 게시글에 대한 댓글 리스트 조회, EMPTY 인 경우가 존재함
        List<CommentEntity> commentEntityList = commentService.getCommentListBy(boardId);
        List<CommentDetailResponse> commentDetailList = commentConverter.toResponse(
            commentEntityList);

        return boardConverter.toResponse(boardEntity, commentDetailList);
    }

}
