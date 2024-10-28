package pet.domain.vaccination.converter;

import db.domain.vaccination.VaccinationEntity;
import global.annotation.Converter;
import java.time.LocalDateTime;
import java.util.List;
import pet.domain.vaccination.controller.model.VaccinationDetailResponse;
import pet.domain.vaccination.controller.model.VaccinationRequest;
import pet.domain.vaccination.controller.model.VaccinationResponse;

@Converter
public class VaccinationConverter {

    public VaccinationEntity toEntity(VaccinationRequest registerRequest, Long userId) {
        return VaccinationEntity.builder()
            .type(registerRequest.getType())
            .date(registerRequest.getDate()) // 실제 접종 날짜
            .registeredAt(LocalDateTime.now()) // 해당 요청이 등록된 시점
            .petId(registerRequest.getPetId())
            .userId(userId)
//            .hospitalId(registerRequest.getHospitalId())
            .build();
    }

    public VaccinationResponse toResponse(VaccinationEntity vaccinationEntity) {
        return VaccinationResponse.builder()
            .vaccinationRecordId(vaccinationEntity.getId())
            .type(vaccinationEntity.getType())
            .date(vaccinationEntity.getDate())
            .petId(vaccinationEntity.getPetId())
            .build();
    }

    public List<VaccinationDetailResponse> toResponse(List<VaccinationEntity> vaccinationEntityList) {
        return vaccinationEntityList.stream()
            .map(vaccinationEntity ->
                this.listToResponse(vaccinationEntity)
            )
            .toList();
    }

    private VaccinationDetailResponse listToResponse(VaccinationEntity vaccinationEntity) {
        return VaccinationDetailResponse.builder()
            .type(vaccinationEntity.getType())
            .date(vaccinationEntity.getDate())
            .registeredAt(vaccinationEntity.getRegisteredAt())
            .petId(vaccinationEntity.getPetId())
            .userId(vaccinationEntity.getUserId())
//            .hospitalId(vaccinationEntity.getHospitalId())
            .build();
    }

}
