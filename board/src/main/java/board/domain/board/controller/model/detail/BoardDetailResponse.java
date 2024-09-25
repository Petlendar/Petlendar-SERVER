package board.domain.board.controller.model.detail;

import db.domain.board.enums.BoardStatus;
import db.domain.pet.enums.PetCategory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDetailResponse {

    private Long id;

    private String title;

    private String content;

    private PetCategory category;

    private BoardStatus status;

    private LocalDateTime registeredAt;

    private LocalDateTime modifiedAt;

    private Long userId;

    private List<CommentDetailResponse> commentList;

}
