package board.domain.board.controller.model;

import db.domain.board.enums.BoardStatus;
import db.domain.pet.enums.PetCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRegisterResponse {

    private Long boardId;

    private String title;

    private PetCategory category;

    private BoardStatus status;

    private Long userId;

}
