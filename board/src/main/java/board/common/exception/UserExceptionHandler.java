package board.common.exception;

import global.api.Api;
import global.errorcode.UserErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import board.common.exception.user.ExistUserEmailException;
import board.common.exception.user.ExistUserNameException;
import board.common.exception.user.LoginFailException;
import board.common.exception.user.UserNotFoundException;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Api<Object>> userNotFoundException(UserNotFoundException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Api.ERROR(UserErrorCode.USER_NOT_FOUND));
    }

    @ExceptionHandler(value = ExistUserNameException.class)
    public ResponseEntity<Api<Object>> existUserNameException(ExistUserNameException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(Api.ERROR(UserErrorCode.EXISTS_USER_NAME));
    }

    @ExceptionHandler(value = ExistUserEmailException.class)
    public ResponseEntity<Api<Object>> existUserEmailException(ExistUserEmailException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(Api.ERROR(UserErrorCode.EXISTS_USER_EMAIL));
    }

    @ExceptionHandler(value = LoginFailException.class)
    public ResponseEntity<Api<Object>> loginFailException(LoginFailException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Api.ERROR(UserErrorCode.LOGIN_FAIL));
    }

}
