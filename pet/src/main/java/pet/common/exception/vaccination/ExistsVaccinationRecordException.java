package pet.common.exception.vaccination;

import global.errorcode.ErrorCodeIfs;

public class ExistsVaccinationRecordException extends RuntimeException {

    private final ErrorCodeIfs errorCodeIfs;
    private final String description;

    public ExistsVaccinationRecordException(ErrorCodeIfs errorCodeIfs) {
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.description = errorCodeIfs.getDescription();
    }

    public ExistsVaccinationRecordException(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        this.errorCodeIfs = errorCodeIfs;
        this.description = errorDescription;
    }

    public ExistsVaccinationRecordException(ErrorCodeIfs errorCodeIfs, Throwable throwable) {
        super(throwable);
        this.errorCodeIfs = errorCodeIfs;
        this.description = errorCodeIfs.getDescription();
    }

    public ExistsVaccinationRecordException(ErrorCodeIfs errorCodeIfs, Throwable throwable,
        String errorDescription) {
        super(throwable);
        this.errorCodeIfs = errorCodeIfs;
        this.description = errorDescription;
    }

}