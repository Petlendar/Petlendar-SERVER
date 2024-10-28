package pet.domain.vaccination.business;

import db.domain.vaccination.VaccinationEntity;
import global.annotation.Business;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pet.domain.pet.service.PetService;
import pet.domain.vaccination.controller.model.VaccinationDetailResponse;
import pet.domain.vaccination.controller.model.VaccinationRequest;
import pet.domain.vaccination.controller.model.VaccinationResponse;
import pet.domain.vaccination.converter.VaccinationConverter;
import pet.domain.vaccination.service.VaccinationService;

@Business
@RequiredArgsConstructor
public class VaccinationBusiness {

    private final VaccinationService vaccinationService;
    private final PetService petService;
    private final VaccinationConverter vaccinationConverter;

    public VaccinationResponse register(VaccinationRequest registerRequest, Long userId) {

        // 존재 유무 확인 -> type, userId, petId, date 로 비교
        vaccinationService.existsByVaccinationRecordWithThrow(registerRequest, userId);

        VaccinationEntity vaccinationEntity = vaccinationConverter.toEntity(registerRequest, userId);
        VaccinationEntity savedVaccinationEntity = vaccinationService.register(vaccinationEntity);

        return vaccinationConverter.toResponse(savedVaccinationEntity);

    }

}
