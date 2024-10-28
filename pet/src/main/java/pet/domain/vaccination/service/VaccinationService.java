package pet.domain.vaccination.service;

import db.domain.vaccination.VaccinationEntity;
import db.domain.vaccination.VaccinationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.domain.vaccination.controller.model.VaccinationRequest;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    public void existsByVaccinationRecordWithThrow(VaccinationRequest request, Long userId) {
        // 존재 유무 확인 -> type, userId, petId, date 로 비교
        Boolean existsByVaccinationRecord = vaccinationRepository.existsByTypeAndDateAndUserIdAndPetId(
            request.getType(), request.getDate(), userId, request.getPetId());
        if (existsByVaccinationRecord) {
            throw new RuntimeException("이미 등록된 예방 접종 기록입니다."); //TODO 예외 처리
        }

    }

}
