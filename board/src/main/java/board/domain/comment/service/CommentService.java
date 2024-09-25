package board.domain.comment.service;

import db.domain.comment.CommentEntity;
import db.domain.comment.CommentRepository;
import db.domain.comment.enums.CommentStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentEntity register(CommentEntity commentEntity, Long userId) {
        commentEntity.setStatus(CommentStatus.REGISTERED);
        commentEntity.setRegisteredAt(LocalDateTime.now());
        commentEntity.setUserId(userId);
        return commentRepository.save(commentEntity);
    }

}
