package board.domain.board.business;

import board.common.response.MessageConverter;
import board.common.response.MessageResponse;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.domain.board.converter.BoardConverter;
import board.domain.board.service.BoardService;
import db.domain.board.BoardEntity;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class BoardBusiness {

    private final BoardService boardService;
    private final BoardConverter boardConverter;
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
}
