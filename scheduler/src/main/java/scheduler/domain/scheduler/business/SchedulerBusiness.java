package scheduler.domain.scheduler.business;

import db.domain.pet.PetEntity;
import db.domain.pet.enums.PetStatus;
import global.annotation.Business;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import scheduler.domain.calendar.business.CalendarBusiness;
import scheduler.domain.calendar.controller.model.VaccinationDateResponse;
import scheduler.domain.fcm.service.FcmService;
import scheduler.domain.fcm.utils.FcmMessageDto;
import scheduler.domain.pet.service.PetService;

@Business
@RequiredArgsConstructor
public class SchedulerBusiness {

    private final CalendarBusiness calendarBusiness;
    private final PetService petService;
    private final FcmService fcmService;

    @Scheduled(cron = "0 0 8 * * *") // 매일 오전 8시에 실행
    public void sendVaccinationReminder() {
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(3); // 3일 전부터 오늘까지

        List<PetEntity> petEntityList = petService.getAllPetWithThrowBy(PetStatus.REGISTERED);

        for (PetEntity petEntity : petEntityList) {
            List<VaccinationDateResponse> upcomingVaccinations =
                calendarBusiness.getNextVaccinationDate(today.getYear(), today.getMonthValue(), petEntity.getUserId());

            for (VaccinationDateResponse vaccination : upcomingVaccinations) {
                LocalDate vaccinationDate = LocalDate.of(vaccination.getYear(), vaccination.getMonth(), vaccination.getDay());

                // 접종일이 3일 전부터 오늘까지의 범위에 속하는지 확인
                if (!vaccinationDate.isBefore(startDate) && !vaccinationDate.isAfter(today)) {
                    sendFcmNotification(petEntity, vaccination); // 알림 전송 메서드
                }
            }
        }
    }

    // 알림 전송 예시 메서드
    private void sendFcmNotification(PetEntity petEntity, VaccinationDateResponse vaccination) {

        String message = String.format("%s의 %s 접종일이 %s입니다.",
            petEntity.getName(),
            vaccination.getDoseType(),
            vaccination.getYear() + "-" + vaccination.getMonth() + "-" + vaccination.getDay());

        FcmMessageDto fcmMessageDto = FcmMessageDto.builder()
            .title("곧 예방 접종해야 해요!")
            .body(message)
            .build();

        fcmService.sendNotification(petEntity.getUserId(), fcmMessageDto);
    }
}