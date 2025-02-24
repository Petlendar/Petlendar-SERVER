package board.domain.board.controller.model.register;

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
public class BoardRegisterResponse {

    private Long boardId;

    private String title;

    private PetCategory category;

    private BoardStatus status;

    private LocalDateTime registeredAt;

    private Long userId;

}
