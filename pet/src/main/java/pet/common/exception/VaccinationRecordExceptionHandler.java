package pet.common.exception;

import global.api.Api;
import global.errorcode.UserErrorCode;
import global.errorcode.VaccinationRecordErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pet.common.exception.user.UserNotFoundException;
import pet.common.exception.vaccination.ExistsVaccinationRecordException;
import pet.common.exception.vaccination.VaccinationRecordNotFoundException;

@RestControllerAdvice
@Slf4j
public class VaccinationRecordExceptionHandler {

    @ExceptionHandler(value = ExistsVaccinationRecordException.class)
    public ResponseEntity<Api<Object>> existsVaccinationRecordException(ExistsVaccinationRecordException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Api.ERROR(VaccinationRecordErrorCode.EXISTS_VACCINATION_RECORD));
    }

    @ExceptionHandler(value = VaccinationRecordNotFoundException.class)
    public ResponseEntity<Api<Object>> vaccinationRecordNotFoundException(VaccinationRecordNotFoundException e) {
        log.info("", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(Api.ERROR(VaccinationRecordErrorCode.VACCINATION_RECORD_NOT_FOUND));
    }


}
