package db.domain.vaccination.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VaccinationType {

    DHIPPL("혼합예방주사", 49, 28, 4, 365), // 기초: 49일 (7주), 추가: 28일, 보강: 365일
    CORONA_VIRUS("코로나 바이러스성 장염", 49, 28, 2, 365), // 기초: 49일, 추가: 28일, 보강: 365일
    KENNEL_COUGH("기관, 기관지염", 49, 28, 2, 365), // 기초: 49일, 추가: 28일, 보강: 365일
    RABIES("광견병", 90, 0, 0, 180); // 기초: 90일 (1회), 추가: 0일, 보강: 180일
    ;

    private final String vaccinationName; // 예방접종 이름
    private final int initialVaccination; // 기초접종 정보
    private final int boosterVaccination; // 추가접종 정보
    private final int boosterVaccinationCount; // 추가접종 횟수
    private final int reinforcementVaccination; // 보강접종 정보

}
