package board.domain.board.service;

import db.domain.board.BoardEntity;
import db.domain.board.BoardRepository;
import db.domain.board.enums.BoardStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardEntity register(BoardEntity boardEntity, Long userId) {
        boardEntity.setUserId(userId);
        boardEntity.setStatus(BoardStatus.REGISTERED);
        boardEntity.setRegisteredAt(LocalDateTime.now());
        return boardRepository.save(boardEntity);
    }

    public BoardEntity getBoardBy(Long boardId) {
        return boardRepository.findFirstByIdAndStatusNotOrderByIdDesc(boardId, BoardStatus.UNREGISTERED)
            .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
    }

    public void notExistsByBoardWithThrow(Long boardId, Long userId) {
        Boolean existsByBoard = boardRepository.existsByIdAndUserIdAndStatusNot(
            boardId, userId, BoardStatus.UNREGISTERED);
        if (!existsByBoard) {
            throw new RuntimeException("게시글이 존재하지 않습니다.");
        }
    }

    public void unregister(BoardEntity boardEntity) {
        boardEntity.setUnregisteredAt(LocalDateTime.now());
        boardEntity.setStatus(BoardStatus.UNREGISTERED);
        boardRepository.save(boardEntity);
    }
}
