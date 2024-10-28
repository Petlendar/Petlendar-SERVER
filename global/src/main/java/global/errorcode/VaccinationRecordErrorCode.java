package global.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VaccinationRecordErrorCode implements ErrorCodeIfs {

    // 1400 ~ 1449
    EXISTS_VACCINATION_RECORD(403, 1400, "이미 등록된 예방접종 기록입니다."),
    VACCINATION_RECORD_NOT_FOUND(404, 1401, "예방접종 기록이 존재하지 않습니다.")
    ;


    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;

}