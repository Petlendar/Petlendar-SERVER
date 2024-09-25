package board.common.exception;

import board.common.error.CommentErrorCode;
import board.common.exception.comment.CommentNotFoundException;
import global.api.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CommentExceptionHandler {

    @ExceptionHandler(value = CommentNotFoundException.class)
    public ResponseEntity<Api<Object>> commentNotFoundException(CommentNotFoundException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Api.ERROR(CommentErrorCode.COMMENT_NOT_FOUND));
    }

}
