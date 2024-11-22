package board.domain.board.converter;

import board.domain.board.controller.model.detail.BoardDetailResponse;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.search.BoardSearchResponse;
import board.domain.board.controller.model.search.SearchCondition;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.domain.image.controller.model.ImageResponse;
import db.domain.board.BoardEntity;
import db.domain.board.EntitySearchCondition;
import db.domain.user.UserEntity;
import global.annotation.Converter;
import java.util.List;
import org.springframework.data.domain.Pageable;

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
        BoardEntity boardEntity, UserEntity userEntity,
        List<ImageResponse> imageResponseList
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
            .name(userEntity.getName())
            .boardImage(imageResponseList)
            .build();
    }

    public BoardRegisterResponse toResponse(BoardEntity boardEntity) {
        return BoardRegisterResponse.builder()
            .boardId(boardEntity.getId())
            .title(boardEntity.getTitle())
            .category(boardEntity.getCategory())
            .status(boardEntity.getStatus())
            .registeredAt(boardEntity.getRegisteredAt())
            .userId(boardEntity.getUserId())
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

    public EntitySearchCondition toEntity(SearchCondition condition, Pageable page) {
        return EntitySearchCondition.builder()
            .boardId(condition.getBoardId())
            .title(condition.getTitle())
            .startDate(condition.getStartDate())
            .endDate(condition.getEndDate())
            .page(page)
            .build();
    }

    public BoardSearchResponse toResponse(BoardEntity boardEntity, UserEntity userEntity, ImageResponse imageResponse) {
        return BoardSearchResponse.builder()
            .boardId(boardEntity.getId())
            .name(userEntity.getName())
            .title(boardEntity.getTitle())
            .registeredAt(boardEntity.getRegisteredAt())
            .status(boardEntity.getStatus())
            .boardImage(imageResponse)
            .build();
    }

}
