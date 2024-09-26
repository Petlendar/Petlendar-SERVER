package global.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentErrorCode implements ErrorCodeIfs {

    // 1350 ~ 1399
    COMMENT_NOT_FOUND(404, 1350, "댓글이 존재하지 않습니다.")
    ;

    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;

}
