package scheduler.config.batch;

import db.domain.token.fcmtoken.FcmTokenEntity;
import db.domain.token.fcmtoken.FcmTokenRepository;
import db.domain.vaccination.VaccinationEntity;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import scheduler.config.fcm.dto.MessageDto;

@Component
@RequiredArgsConstructor
public class VaccinationProcessor implements ItemProcessor<VaccinationEntity, MessageDto> {

    private final FcmTokenRepository fcmTokenRepository;

    @Override
    public MessageDto process(VaccinationEntity vaccinationEntity) throws Exception {
        LocalDate nextVaccinationDate = vaccinationEntity.getType().getNextVaccinationDate(vaccinationEntity.getDate().toLocalDate());

        Long userId = vaccinationEntity.getUserId();

        FcmTokenEntity fcmTokenEntity = fcmTokenRepository.findFirstByUserIdOrderByIdDesc(
            userId);

        // 오늘 날짜와 일치하는 예방접종일인 경우에만 알림 생성
        if (nextVaccinationDate != null && nextVaccinationDate.equals(LocalDate.now())) {
            return MessageDto.builder()
                .title(nextVaccinationDate + "에 " + vaccinationEntity.getType().getVaccinationName() + " 접종일입니다.")
                .content("반려동물 ID: " + vaccinationEntity.getPetId() + "의 예방접종을 잊지 말아주세요!")
                .fcmToken(fcmTokenEntity.getFcmToken())
                .build();
        }
        return null;  // 알림 필요 없음
    }
}
