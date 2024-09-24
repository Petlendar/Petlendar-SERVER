package db.domain.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {

    REGISTERED("등록"),
    UNREGISTERED("탈퇴"),
    DORMANT("휴면")
    ;

    private String description;

}
