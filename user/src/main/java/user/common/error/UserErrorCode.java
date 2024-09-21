package user.common.error;

import global.errorcode.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(404, 1150, "사용자를 찾을 수 없습니다.")
    ;

    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;


}
