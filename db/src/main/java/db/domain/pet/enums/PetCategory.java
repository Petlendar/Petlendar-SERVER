package db.domain.pet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetCategory {

    DOG("개"),
    CAT("고양이")
    ;

    private final String description;

}
