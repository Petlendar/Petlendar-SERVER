package board.domain.board.controller.model.search;

import board.domain.image.controller.model.ImageResponse;
import db.domain.board.enums.BoardStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardSearchResponse {

    private Long boardId;

    private String title;

    private LocalDateTime registeredAt;

    private BoardStatus status;

    private ImageResponse boardImage;

}
