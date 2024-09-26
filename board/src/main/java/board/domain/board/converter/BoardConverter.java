package board.domain.board.converter;

import board.domain.board.controller.model.detail.BoardDetailResponse;
import board.domain.board.controller.model.detail.CommentDetailResponse;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.domain.image.controller.model.ImageResponse;
import db.domain.board.BoardEntity;
import db.domain.comment.CommentEntity;
import db.domain.image.ImageEntity;
import global.annotation.Converter;
import java.util.List;

@Converter
public class BoardConverter {

    public BoardEntity toEntity(BoardRegisterRequest request) {
        return BoardEntity.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .category(request.getCategory())
            .build();

    }

    public BoardDetailResponse toResponse(
        BoardEntity boardEntity,
        List<ImageResponse> imageResponseList,
        List<CommentDetailResponse> commentDetailList
    ) {
        return BoardDetailResponse.builder()
            .id(boardEntity.getUserId())
            .title(boardEntity.getTitle())
            .content(boardEntity.getContent())
            .category(boardEntity.getCategory())
            .status(boardEntity.getStatus())
            .registeredAt(boardEntity.getRegisteredAt())
            .modifiedAt(boardEntity.getModifiedAt())
            .userId(boardEntity.getUserId())
            .boardImage(imageResponseList)
            .commentList(commentDetailList)
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
