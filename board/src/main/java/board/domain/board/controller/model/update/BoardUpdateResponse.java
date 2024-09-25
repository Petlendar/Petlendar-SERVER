package board.domain.board.controller.model.update;

import db.domain.board.enums.BoardStatus;
import db.domain.pet.enums.PetCategory;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateResponse {

    private Long boardId;

    private BoardStatus status;

    private LocalDateTime modifiedAt;

    private Long userId;

}
