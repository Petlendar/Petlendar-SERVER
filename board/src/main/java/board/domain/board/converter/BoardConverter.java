package board.domain.board.converter;

import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import board.domain.board.controller.model.update.BoardUpdateResponse;
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
            .registeredAt(savedBoardEntity.getRegisteredAt())
            .userId(savedBoardEntity.getUserId())
            .build();
    }

    public BoardUpdateResponse toUpdateResponse(BoardEntity savedBoardEntity) {
        return BoardUpdateResponse.builder()
            .boardId(savedBoardEntity.getId())
            .status(savedBoardEntity.getStatus())
            .modifiedAt(savedBoardEntity.getModifiedAt())
            .userId(savedBoardEntity.getUserId())
            .build();
    }

}
