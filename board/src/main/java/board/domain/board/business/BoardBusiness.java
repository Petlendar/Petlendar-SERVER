package board.domain.board.business;

import board.domain.board.controller.model.BoardRegisterRequest;
import board.domain.board.controller.model.BoardRegisterResponse;
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

    public BoardRegisterResponse register(BoardRegisterRequest request, Long userId) {

        BoardEntity boardEntity = boardConverter.toEntity(request);
        BoardEntity savedBoardEntity = boardService.register(boardEntity, userId);

        return boardConverter.toResponse(savedBoardEntity);
    }

}
