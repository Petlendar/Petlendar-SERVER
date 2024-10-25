package db.domain.hospital.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HospitalStatus {

    REGISTERED("등록"),
    UNREGISTERED("삭제")
    ;

    private final String description;

}
