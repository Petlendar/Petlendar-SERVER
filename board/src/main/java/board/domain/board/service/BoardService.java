package board.domain.board.service;

import board.common.error.BoardErrorCode;
import board.common.exception.board.BoardNotFoundException;
import board.domain.board.controller.model.search.BoardSearchResponse;
import board.domain.board.controller.model.update.BoardUpdateRequest;
import db.domain.board.BoardEntity;
import db.domain.board.BoardRepository;
import db.domain.board.EntitySearchCondition;
import db.domain.board.QueryBoardRepository;
import db.domain.board.enums.BoardStatus;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final QueryBoardRepository queryBoardRepository;

    public BoardEntity register(BoardEntity boardEntity, Long userId) {
        boardEntity.setUserId(userId);
        boardEntity.setStatus(BoardStatus.REGISTERED);
        boardEntity.setRegisteredAt(LocalDateTime.now());
        return boardRepository.save(boardEntity);
    }

    public BoardEntity getBoardBy(Long boardId) {
        return boardRepository.findFirstByIdAndStatusNotOrderByIdDesc(boardId,
                BoardStatus.UNREGISTERED)
            .orElseThrow(() -> new BoardNotFoundException(BoardErrorCode.BOARD_NOT_FOUND));
    }

    public void notExistsByBoardWithThrow(Long boardId, Long userId) {
        Boolean existsByBoard = boardRepository.existsByIdAndUserIdAndStatusNot(
            boardId, userId, BoardStatus.UNREGISTERED);
        if (!existsByBoard) {
            throw new BoardNotFoundException(BoardErrorCode.BOARD_NOT_FOUND);
        }
    }

    public void notExistsByBoardWithThrow(Long boardId) {
        Boolean existsByBoard = boardRepository.existsByIdAndStatusNot(
            boardId, BoardStatus.UNREGISTERED);
        if (!existsByBoard) {
            throw new BoardNotFoundException(BoardErrorCode.BOARD_NOT_FOUND);
        }
    }

    public void unregister(BoardEntity boardEntity) {
        boardEntity.setUnregisteredAt(LocalDateTime.now());
        boardEntity.setStatus(BoardStatus.UNREGISTERED);
        boardRepository.save(boardEntity);
    }

    public BoardEntity update(BoardEntity boardEntity, BoardUpdateRequest updateRequest) {
        boardEntity.setTitle(updateRequest.getTitle());
        boardEntity.setContent(updateRequest.getContent());
        boardEntity.setCategory(updateRequest.getCategory());
        boardEntity.setStatus(BoardStatus.MODIFIED);
        boardEntity.setModifiedAt(LocalDateTime.now());
        return boardRepository.save(boardEntity);
    }

    public List<BoardEntity> getBoardSearchBy(EntitySearchCondition searchCondition) {
        List<BoardEntity> searchList = queryBoardRepository.getBoardSearchBy(searchCondition);
        if (searchList.isEmpty()) {
            throw new BoardNotFoundException(BoardErrorCode.BOARD_NOT_FOUND);
        }
        return searchList;
    }

}
