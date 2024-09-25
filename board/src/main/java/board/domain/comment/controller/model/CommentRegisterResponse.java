package board.domain.comment.controller.model;

import db.domain.comment.enums.CommentStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRegisterResponse {

    private Long commentId;

    private CommentStatus status;

    private LocalDateTime registeredAt;

    private Long boardId;

    private Long userId;

}
