package pet.domain.vaccination.service;

import db.domain.vaccination.VaccinationEntity;
import db.domain.vaccination.VaccinationRepository;
import global.errorcode.VaccinationRecordErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.common.exception.vaccination.ExistsVaccinationRecordException;
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
            throw new ExistsVaccinationRecordException(
                VaccinationRecordErrorCode.EXISTS_VACCINATION_RECORD);
        }

    }

    public VaccinationEntity register(VaccinationEntity vaccinationEntity) {
        return vaccinationRepository.save(vaccinationEntity);
    }

    public List<VaccinationEntity> getVaccinationRecordListBy(Long petId) {
        List<VaccinationEntity> vaccinationEntityList = vaccinationRepository.findAllByPetIdOrderByIdAsc(
            petId);

        if (vaccinationEntityList.isEmpty()) {
            throw new ExistsVaccinationRecordException(
                VaccinationRecordErrorCode.VACCINATION_RECORD_NOT_FOUND);
        }

        return vaccinationEntityList;
    }
}
