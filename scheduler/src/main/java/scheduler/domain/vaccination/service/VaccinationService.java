package scheduler.domain.vaccination.service;

import db.domain.vaccination.VaccinationEntity;
import db.domain.vaccination.VaccinationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationService {

    private final VaccinationRepository vaccinationRepository;

    public List<VaccinationEntity> getVaccinationRecordListWithoutExceptionBy(Long petId) {
        List<VaccinationEntity> vaccinationEntityList = vaccinationRepository.findAllByPetIdOrderByIdAsc(
            petId);
        return vaccinationEntityList;
    }
}
