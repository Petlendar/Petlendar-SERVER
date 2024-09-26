package pet.common.exception;

import global.api.Api;
import global.errorcode.PetErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pet.common.exception.pet.ExistsPetException;
import pet.common.exception.pet.PetNotFoundException;

@RestControllerAdvice
@Slf4j
public class PetExceptionHandler {

    @ExceptionHandler(value = PetNotFoundException.class)
    public ResponseEntity<Api<Object>> petNotFoundException(PetNotFoundException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Api.ERROR(PetErrorCode.PET_NOT_FOUND));
    }

    @ExceptionHandler(value = ExistsPetException.class)
    public ResponseEntity<Api<Object>> existsPetException(ExistsPetException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Api.ERROR(PetErrorCode.EXISTS_PET));
    }


}
