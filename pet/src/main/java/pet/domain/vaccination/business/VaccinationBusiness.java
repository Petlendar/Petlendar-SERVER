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

        // 반려동물이 존재하는지 확인, 없으면 예외
        petService.notExistsByPetWithThrow(registerRequest.getPetId(), userId);

        // 존재 유무 확인 -> type, userId, petId, date 로 비교
        vaccinationService.existsByVaccinationRecordWithThrow(registerRequest, userId);

        VaccinationEntity vaccinationEntity = vaccinationConverter.toEntity(registerRequest, userId);
        VaccinationEntity savedVaccinationEntity = vaccinationService.register(vaccinationEntity);

        return vaccinationConverter.toResponse(savedVaccinationEntity);

    }


    public List<VaccinationDetailResponse> getVaccinationRecordList(Long petId, Long userId) {

        // 반려동물이 존재하는지 확인, 없으면 예외
        petService.notExistsByPetWithThrow(petId, userId);

        // 해당 반려동물의 접종 기록이 존재하는지 확인
        vaccinationService.notExistsByVaccinationRecordWithThrow(petId);

        // 해당 반려동물의 모든 접종기록 조회
        List<VaccinationEntity> vaccinationEntityList = vaccinationService.getVaccinationRecordListBy(petId);

        // Response 로 변경
        return vaccinationConverter.toResponse(vaccinationEntityList);

    }

}
