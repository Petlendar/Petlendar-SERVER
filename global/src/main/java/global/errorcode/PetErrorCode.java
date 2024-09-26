package global.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetErrorCode implements ErrorCodeIfs {

    // 1200 ~ 1249
    PET_NOT_FOUND(404, 1200, "반려동물을 찾을 수 없습니다."),
    EXISTS_PET(403, 1201, "이미 존하는 반려동물입니다."),
    ;

    private final Integer httpCode;
    private final Integer errorCode;
    private final String description;

}