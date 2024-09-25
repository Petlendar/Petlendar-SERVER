package board.domain.comment.controller.model.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentRegisterRequest {

    private String content;

    private Long boardId;

}
