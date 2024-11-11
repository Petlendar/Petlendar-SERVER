package scheduler.domain.calendar.business;

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
import lombok.extern.slf4j.Slf4j;
import scheduler.domain.calendar.controller.model.VaccinationDateResponse;
import scheduler.domain.calendar.controller.model.VaccinationDateResponse.DoseType;
import scheduler.domain.pet.service.PetService;
import scheduler.domain.vaccination.service.VaccinationService;

@Business
@RequiredArgsConstructor
@Slf4j
public class CalendarBusiness {

    private final PetService petService;
    private final VaccinationService vaccinationService;

    public List<VaccinationDateResponse> getNextVaccinationDate(int year, int month, Long userId) {
        // 등록된 반려동물 리스트 가져오기
        List<PetEntity> petEntityList = petService.getPetListBy(userId, PetStatus.REGISTERED);
        List<VaccinationDateResponse> vaccinationDateList = new ArrayList<>();

        for (PetEntity petEntity : petEntityList) {

            // 생일 추가
            LocalDate birthday = petEntity.getBirth();
            LocalDate targetBirthday = LocalDate.of(year, birthday.getMonthValue(), birthday.getDayOfMonth());
            if (targetBirthday.getMonthValue() == month) {
                vaccinationDateList.add(createResponse(year, month, targetBirthday, null, petEntity, DoseType.BIRTHDAY));
            }

            // 각 반려동물의 예방접종 기록 조회
            List<VaccinationEntity> vaccinationEntityList = vaccinationService.getVaccinationRecordListWithoutExceptionBy(
                petEntity.getId());

            // 백신 타입별로 가장 최근 접종 기록을 저장할 Map
            Map<VaccinationType, VaccinationEntity> latestVaccinationMap = new HashMap<>();

            // 각 예방접종 기록을 백신 타입별로 최신 데이터로 업데이트
            for (VaccinationEntity vaccinationEntity : vaccinationEntityList) {
                VaccinationType type = vaccinationEntity.getType();

                // 백신이 이미 Map 에 존재하고, 기존 날짜보다 최신이면 업데이트
                if(latestVaccinationMap.containsKey(type)) {
                    if(vaccinationEntity.getDate().isAfter(latestVaccinationMap.get(type).getDate())) {
                        latestVaccinationMap.put(type, vaccinationEntity);
                    }
                } else {
                    // 백신이 처음 들어오는 경우 바로 추가
                    latestVaccinationMap.put(type, vaccinationEntity);
                }
            }

            // 각 백신 타입에 대해 접종 일정을 계산
            for (VaccinationType type : VaccinationType.values()) {
                VaccinationEntity lastRecord = latestVaccinationMap.get(type);
                LocalDate baseDate = lastRecord != null ? lastRecord.getDate().toLocalDate() : petEntity.getBirth();

                // 1. 기초접종 계산
                LocalDate initialDate = petEntity.getBirth().plusWeeks(type.getInitialWeeks());
                if (initialDate.getYear() == year && initialDate.getMonthValue() == month) {
                    vaccinationDateList.add(createResponse(year, month, initialDate, type, petEntity, DoseType.INITIAL));
                }

                // 2. 추가접종 계산
                for (int i = 1; i <= type.getBoosterCount(); i++) {
                    LocalDate boosterDate = initialDate.plusWeeks(i * type.getBoosterWeeks());
                    if (boosterDate.isAfter(baseDate) && boosterDate.getYear() == year && boosterDate.getMonthValue() == month) {
                        vaccinationDateList.add(createResponse(year, month, boosterDate, type, petEntity, DoseType.BOOSTER));
                    }
                }

                // 3. 보강접종 계산 (매년 주기로 반복)
                LocalDate reinforcementDate = baseDate.plusWeeks(type.getReinforcementWeeks());
                while (reinforcementDate.getYear() <= year) {
                    reinforcementDate = reinforcementDate.plusYears(1);
                    if (reinforcementDate.getYear() == year && reinforcementDate.getMonthValue() == month) {
                        vaccinationDateList.add(createResponse(year, month, reinforcementDate, type, petEntity, DoseType.REINFORCEMENT));
                    }
                }
            }
        }
        return vaccinationDateList;
    }

    private VaccinationDateResponse createResponse(int year, int month, LocalDate date,
        VaccinationType type, PetEntity petEntity, DoseType doseType) {
        return VaccinationDateResponse.builder()
            .year(year)
            .month(month)
            .day(date.getDayOfMonth())
            .vaccinationType(type)
            .petId(petEntity.getId())
            .doseType(doseType)
            .build();
    }

}