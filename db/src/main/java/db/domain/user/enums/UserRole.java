package db.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    BASIC("사용자"),
    MANAGER("관리자"),
    HOSPITAL("병원")
    ;

    private final String description;

}
