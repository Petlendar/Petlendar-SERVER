package board.domain.comment.converter;

import board.domain.comment.controller.model.detail.CommentDetailResponse;
import board.domain.comment.controller.model.register.CommentRegisterRequest;
import board.domain.comment.controller.model.register.CommentRegisterResponse;
import board.domain.comment.controller.model.update.CommentUpdateResponse;
import board.domain.user.service.UserService;
import db.domain.comment.CommentEntity;
import db.domain.user.UserEntity;
import global.annotation.Converter;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class CommentConverter {

    private final UserService userService;

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

    public List<CommentDetailResponse> toResponse(List<CommentEntity> commentEntityList) {
        return commentEntityList.stream()
            .map(commentEntity ->
                this.toDetailResponse(commentEntity)
            ).toList();
    }

    public CommentDetailResponse toDetailResponse(CommentEntity commentEntity) {
        UserEntity userEntity = userService.getUserWithThrow(commentEntity.getUserId());
        return CommentDetailResponse.builder()
            .commentId(commentEntity.getId())
            .content(commentEntity.getContent())
            .status(commentEntity.getStatus())
            .registeredAt(commentEntity.getRegisteredAt())
            .modifiedAt(commentEntity.getModifiedAt())
            .userId(commentEntity.getUserId())
            .name(userEntity.getName())
            .boardId(commentEntity.getBoardId())
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
