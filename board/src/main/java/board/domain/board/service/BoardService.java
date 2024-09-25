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
}
