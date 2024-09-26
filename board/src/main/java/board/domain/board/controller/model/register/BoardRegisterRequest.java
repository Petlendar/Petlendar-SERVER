package board.domain.board.controller.model.register;

import db.domain.pet.enums.PetCategory;
import java.util.List;
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

    private List<Long> imageIdList;

}
