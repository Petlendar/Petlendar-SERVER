package board.domain.comment.controller.model.update;

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
public class CommentUpdateResponse {

    private Long commentId;

    private CommentStatus status;

    private LocalDateTime modifiedAt;

    private Long userId;

}
