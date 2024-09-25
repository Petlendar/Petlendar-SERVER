package board.domain.board.controller.model.update;

import db.domain.pet.enums.PetCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {

    private Long boardId;

    private String title;

    private String content;

    private PetCategory category;

}
