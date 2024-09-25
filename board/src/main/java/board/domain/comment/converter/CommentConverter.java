package board.domain.comment.converter;

import board.domain.comment.controller.model.CommentRegisterRequest;
import board.domain.comment.controller.model.CommentRegisterResponse;
import db.domain.comment.CommentEntity;
import global.annotation.Converter;

@Converter
public class CommentConverter {

    public CommentEntity toEntity(CommentRegisterRequest registerRequest) {
        return CommentEntity.builder()
            .content(registerRequest.getContent())
            .boardId(registerRequest.getBoardId())
            .build();
    }

    public CommentRegisterResponse toResponse(CommentEntity commentEntity) {
        return CommentRegisterResponse.builder()
            .commentId(commentEntity.getId())
            .status(commentEntity.getStatus())
            .registeredAt(commentEntity.getRegisteredAt())
            .boardId(commentEntity.getBoardId())
            .userId(commentEntity.getUserId())
            .build();
    }

}
