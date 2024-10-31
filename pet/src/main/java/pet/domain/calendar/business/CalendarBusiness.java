package pet.domain.calendar.business;

import db.domain.pet.PetEntity;
import db.domain.pet.enums.PetStatus;
import db.domain.vaccination.VaccinationEntity;
import db.domain.vaccination.enums.VaccinationType;
import global.annotation.Business;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import pet.domain.calendar.controller.model.VaccinationDateResponse;
import pet.domain.pet.service.PetService;
import pet.domain.vaccination.service.VaccinationService;

@Business
@RequiredArgsConstructor
public class CalendarBusiness {

    private final PetService petService;
    private final VaccinationService vaccinationService;

    public List<VaccinationDateResponse> getNextVaccinationDate(int year, int month, Long userId) {

        List<PetEntity> petEntityList = petService.getPetListBy(userId, PetStatus.REGISTERED);
        List<VaccinationDateResponse> vaccinationDateList = new ArrayList<>();

        for (PetEntity petEntity : petEntityList) {
            List<VaccinationEntity> vaccinationEntityList = vaccinationService.getVaccinationRecordListBy(petEntity.getId());

            // 각 백신 타입별로 가장 최근 접종 기록을 찾음
            Map<VaccinationType, VaccinationEntity> latestVaccinationMap = new HashMap<>();

            for (VaccinationEntity vaccinationEntity : vaccinationEntityList) {
                VaccinationType type = vaccinationEntity.getType();

                // 이미 같은 타입의 예방접종이 들어가 있는 경우 날짜 비교
                if (latestVaccinationMap.containsKey(type)) {
                    // 현재 저장된 백신 날짜와 비교해서 더 최신일 경우에만 업데이트
                    if (vaccinationEntity.getDate().isAfter(latestVaccinationMap.get(type).getDate())) {
                        latestVaccinationMap.put(type, vaccinationEntity);
                    }
                } else {
                    // 해당 타입의 백신이 처음 들어가는 경우 바로 추가
                    latestVaccinationMap.put(type, vaccinationEntity);
                }
            }

            for (VaccinationEntity vaccinationEntity : latestVaccinationMap.values()) {
                LocalDate recordedVaccinationDate = vaccinationEntity.getDate().toLocalDate();

                // 1. Initial Vaccination 계산
                LocalDate initialVaccinationDate = recordedVaccinationDate.plusDays(vaccinationEntity.getType().getInitialVaccination());
                if (initialVaccinationDate.getYear() == year && initialVaccinationDate.getMonthValue() == month) {
                    vaccinationDateList.add(createResponse(year, month, initialVaccinationDate, vaccinationEntity, petEntity));
                }

                // 2. Booster Vaccination 계산 (횟수 제한 포함)
                for (int i = 1; i <= vaccinationEntity.getType().getBoosterVaccinationCount(); i++) {
                    LocalDate boosterVaccinationDate = recordedVaccinationDate.plusDays(i * vaccinationEntity.getType().getBoosterVaccination());
                    if (boosterVaccinationDate.getYear() == year && boosterVaccinationDate.getMonthValue() == month) {
                        vaccinationDateList.add(createResponse(year, month, boosterVaccinationDate, vaccinationEntity, petEntity));
                    }
                }

                // 3. Reinforcement Vaccination 계산
                LocalDate reinforcementVaccinationDate = recordedVaccinationDate.plusDays(vaccinationEntity.getType().getReinforcementVaccination());
                if (reinforcementVaccinationDate.getYear() == year && reinforcementVaccinationDate.getMonthValue() == month) {
                    vaccinationDateList.add(createResponse(year, month, reinforcementVaccinationDate, vaccinationEntity, petEntity));
                }
            }
        }
        return vaccinationDateList;
    }

    private VaccinationDateResponse createResponse(int year, int month, LocalDate date, VaccinationEntity vaccination, PetEntity petEntity) {
        return VaccinationDateResponse.builder()
            .year(year)
            .month(month)
            .day(date.getDayOfMonth())
            .vaccinationType(vaccination.getType())
            .petId(petEntity.getId())
            .build();
    }

}
