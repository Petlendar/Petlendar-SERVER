package db.domain.pet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetStatus {

    ALIVE("생존"),
    DEAD("사망")
    ;

    private String description;

}
