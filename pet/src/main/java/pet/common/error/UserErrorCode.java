package pet.common.error;

import global.errorcode.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(404, 1150, "사용자를 찾을 수 없습니다."),
    EXISTS_USER_EMAIL(403, 1151, "이미 존재하는 아이디입니다."),
    EXISTS_USER_NAME(403, 1152, "이미 존재하는 이름입니다."),
    LOGIN_FAIL(401, 1153, "로그인 정보가 일치하지 않습니다."),
    USER_UNREGISTER_FAIL(500, 1154, "회원 탈퇴에 실패했습니다.")
    ;

    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;

}
