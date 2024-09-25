package board.domain.comment.converter;

import board.domain.comment.controller.model.register.CommentRegisterRequest;
import board.domain.comment.controller.model.register.CommentRegisterResponse;
import board.domain.comment.controller.model.update.CommentUpdateResponse;
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

    public CommentUpdateResponse toUpdateResponse(CommentEntity updatedEntity) {
        return CommentUpdateResponse.builder()
            .commentId(updatedEntity.getId())
            .status(updatedEntity.getStatus())
            .modifiedAt(updatedEntity.getModifiedAt())
            .userId(updatedEntity.getUserId())
            .build();
    }

}
