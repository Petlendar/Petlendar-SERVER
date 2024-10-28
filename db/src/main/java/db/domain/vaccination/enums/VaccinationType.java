package db.domain.vaccination.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VaccinationType {

    DHIPPL("혼합예방주사"),
    CORONA_VIRUS("코로나 바이러스성 장염"),
    KENNEL_COUGH("기관, 기관지염"),
    RABIES("광견병")
    ;

    private final String description;
}
