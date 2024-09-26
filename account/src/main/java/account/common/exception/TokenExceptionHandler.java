package account.common.exception;

import global.api.Api;
import global.errorcode.TokenErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import account.common.exception.jwt.TokenException;
import account.common.exception.jwt.TokenExpiredException;
import account.common.exception.jwt.TokenSignatureException;

@RestControllerAdvice
@Slf4j
public class TokenExceptionHandler {

    @ExceptionHandler(value = TokenSignatureException.class)
    public ResponseEntity<Api<Object>> tokenSignatureException(TokenSignatureException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Api.ERROR(TokenErrorCode.INVALID_TOKEN));
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public ResponseEntity<Api<Object>> tokenExpiredException(TokenExpiredException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Api.ERROR(TokenErrorCode.EXPIRED_TOKEN));
    }

    @ExceptionHandler(value = TokenException.class)
    public ResponseEntity<Api<Object>> tokenException(TokenException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(Api.ERROR(TokenErrorCode.TOKEN_EXCEPTION));
    }

}
