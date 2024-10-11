package db.domain.board.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardStatus {

    REGISTERED("등록"),
    UNREGISTERED("삭제"),
    MODIFIED("수정")
    ;

    private final String description;

}
