package board.domain.board.business;

import board.common.response.MessageConverter;
import board.common.response.MessageResponse;
import board.domain.board.controller.model.detail.BoardDetailResponse;
import board.domain.board.controller.model.register.BoardRegisterRequest;
import board.domain.board.controller.model.register.BoardRegisterResponse;
import board.domain.board.controller.model.search.BoardSearchResponse;
import board.domain.board.controller.model.search.SearchCondition;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import board.domain.board.controller.model.update.BoardUpdateResponse;
import board.domain.board.converter.BoardConverter;
import board.domain.board.service.BoardService;
import board.domain.image.controller.model.ImageResponse;
import board.domain.image.converter.ImageConverter;
import board.domain.image.service.ImageService;
import board.domain.user.service.UserService;
import db.domain.board.BoardEntity;
import db.domain.board.EntitySearchCondition;
import db.domain.image.ImageEntity;
import db.domain.user.UserEntity;
import global.annotation.Business;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@Business
@RequiredArgsConstructor
public class BoardBusiness {

    private final BoardService boardService;
    private final ImageService imageService;
    private final UserService userService;
    private final BoardConverter boardConverter;
    private final MessageConverter messageConverter;
    private final ImageConverter imageConverter;

    public BoardRegisterResponse register(BoardRegisterRequest request, Long userId) {

        BoardEntity boardEntity = boardConverter.toEntity(request);
        BoardEntity savedBoardEntity = boardService.register(boardEntity, userId);

        imageService.linkImageToBoard(request.getImageIdList(), savedBoardEntity);

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

        Long userId = boardEntity.getUserId();

        UserEntity userEntity = userService.getUserWithThrow(userId);

        // 이미지 Entity 조회
        List<ImageEntity> imageEntityList = imageService.getImageOrEmptyBy(boardId);
        List<ImageResponse> imageResponseList = imageEntityList.stream()
            .map(imageEntity -> imageConverter.toResponse(imageEntity))
            .toList();

        return boardConverter.toResponse(boardEntity, userEntity, imageResponseList);
    }

    public List<BoardSearchResponse> getBoardSearchBy(SearchCondition condition, Pageable page) {

        EntitySearchCondition entitySearchCondition = boardConverter.toEntity(condition, page);
        List<BoardEntity> boardEntityList = boardService.getBoardSearchBy(entitySearchCondition);

        return boardEntityList.stream().map(boardEntity -> {

            ImageEntity firstImageEntity = getFirstImageBy(boardEntity.getId());
            ImageResponse boardImageResponse = (firstImageEntity != null) ? imageConverter.toResponse(firstImageEntity) : null;
            UserEntity userEntity = userService.getUserWithThrow(boardEntity.getUserId());

            return boardConverter.toResponse(boardEntity, userEntity, boardImageResponse);
        }).toList();
    }

    private ImageEntity getFirstImageBy(Long boardId) {
        List<ImageEntity> imageEntityList = imageService.getImageOrEmptyBy(boardId);
        return imageEntityList.isEmpty() ? null : imageEntityList.get(0);  // 첫 번째 이미지, 없으면 null 반환
    }

}
