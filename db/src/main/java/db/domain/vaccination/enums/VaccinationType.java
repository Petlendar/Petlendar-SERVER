package db.domain.vaccination.enums;

import java.time.LocalDate;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VaccinationType {

    DHPPL("혼합예방주사", 6, 4, 3, 52),         // 혼합예방주사: 6주에 기초접종, 4주 간격 추가접종 3회, 52주 간격 보강접종
    CORONAVIRUS("코로나 바이러스성 장염" , 6, 4, 2, 52),   // 코로나바이러스성 장염: 6주에 기초접종, 4주 간격 추가접종 2회, 52주 간격 보강접종
    KENNEL_COUGH("기관, 기관지염" , 6, 4, 2, 52),  // 기관지염: 6주에 기초접종, 4주 간격 추가접종 2회, 52주 간격 보강접종
    RABIES("광견병", 12, 26, 1, 26);      // 광견병: 12주에 기초접종, 26주 간격 보강접종 (추가접종 없음)

    private final String vaccinationName; // 예방접종 이름
    private final int initialWeeks;  // 생후 몇 주에 기초접종
    private final int boosterWeeks;  // 추가접종 주기 (주 단위)
    private final int boosterCount;  // 추가접종 횟수
    private final int reinforcementWeeks;  // 보강접종 주기 (주 단위)

    // 다음 접종 날짜 계산
    public LocalDate getNextVaccinationDate(LocalDate lastVaccinationDate) {
        LocalDate initialDate = lastVaccinationDate.plusDays(initialWeeks);
        LocalDate boosterDate = lastVaccinationDate.plusDays(boosterWeeks);
        LocalDate reinforcementDate = lastVaccinationDate.plusDays(reinforcementWeeks);

        // 주기가 0인 경우는 제외하고 가장 빠른 날짜를 계산
        return Stream.of(initialDate, boosterDate, reinforcementDate)
            .filter(date -> date != null && !date.isBefore(LocalDate.now()))
            .min(LocalDate::compareTo)
            .orElse(null);

    }

}
