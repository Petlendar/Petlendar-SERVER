package board.domain.board.controller.model.search;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private Long boardId;

    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
