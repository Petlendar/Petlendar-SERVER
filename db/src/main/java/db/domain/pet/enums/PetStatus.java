package db.domain.pet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetStatus {

    REGISTERED("등록"),
    UNREGISTERED("삭제")
    ;

    private final String description;

}
