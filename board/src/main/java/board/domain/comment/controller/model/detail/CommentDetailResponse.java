package board.domain.comment.controller.model.detail;

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
public class CommentDetailResponse {

    private Long commentId;

    private String content;

    private CommentStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime modifiedAt;

    private Long userId;

    private String name;

    private Long boardId;

}
