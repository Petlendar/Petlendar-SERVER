package board.domain.board.controller.model;

import db.domain.pet.enums.PetCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterRequest {

    private String title;

    private String content;

    private PetCategory category;

}
