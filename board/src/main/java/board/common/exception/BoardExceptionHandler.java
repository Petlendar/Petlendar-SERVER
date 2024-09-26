package board.common.exception;

import board.common.exception.board.BoardNotFoundException;
import global.api.Api;
import global.errorcode.BoardErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BoardExceptionHandler {

    @ExceptionHandler(value = BoardNotFoundException.class)
    public ResponseEntity<Api<Object>> boardNotFoundException(BoardNotFoundException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Api.ERROR(BoardErrorCode.BOARD_NOT_FOUND));
    }

}
