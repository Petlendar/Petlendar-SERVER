package db.domain.board;

import db.domain.board.enums.BoardStatus;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EntitySearchCondition {

    private Long boardId;

    private String title;

    private Pageable page;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
