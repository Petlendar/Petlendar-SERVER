package board.domain.board.converter;

import board.domain.board.controller.model.BoardRegisterRequest;
import board.domain.board.controller.model.BoardRegisterResponse;
import db.domain.board.BoardEntity;
import global.annotation.Converter;

@Converter
public class BoardConverter {

    public BoardEntity toEntity(BoardRegisterRequest request) {
        return BoardEntity.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .category(request.getCategory())
            .build();

    }

    public BoardRegisterResponse toResponse(BoardEntity savedBoardEntity) {
        return BoardRegisterResponse.builder()
            .boardId(savedBoardEntity.getId())
            .title(savedBoardEntity.getTitle())
            .category(savedBoardEntity.getCategory())
            .status(savedBoardEntity.getStatus())
            .userId(savedBoardEntity.getUserId())
            .build();
    }

}
