package global.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BoardErrorCode implements ErrorCodeIfs {

    // 1300 ~ 1349
    BOARD_NOT_FOUND(404, 1300, "게시글이 존재하지 않습니다.")
    ;

    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;

}