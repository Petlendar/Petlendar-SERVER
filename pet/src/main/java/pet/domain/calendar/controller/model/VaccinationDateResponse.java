package pet.domain.calendar.controller.model;

import db.domain.vaccination.enums.VaccinationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationDateResponse {

    private int year;

    private int month;

    private int day;

    private VaccinationType vaccinationType;

    private Long petId;

    private DoseType doseType;

    public enum DoseType {
        INITIAL, // 기초접종
        BOOSTER, // 추가접종
        REINFORCEMENT // 보강접종
    }

}
