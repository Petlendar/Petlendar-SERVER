package board.domain.board.controller.model.search;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private Long boardId;

    @Size(min = 2, message = "최소 2자 이상 입력 가능합니다.")
    private String title;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
