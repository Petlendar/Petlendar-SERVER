package pet.common.error;

import global.errorcode.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetErrorCode implements ErrorCodeIfs {

    PET_NOT_FOUND(404, 1300, "반려동물을 찾을 수 없습니다."),
    EXISTS_PET(403, 1301, "이미 존하는 반려동물입니다."),
    ;

    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;

}