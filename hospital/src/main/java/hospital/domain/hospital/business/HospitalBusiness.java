package hospital.domain.hospital.business;

import db.domain.hospital.HospitalEntity;
import db.domain.hospital.enums.HospitalStatus;
import db.domain.pet.PetEntity;
import global.annotation.Business;
import hospital.common.response.MessageConverter;
import hospital.common.response.MessageResponse;
import hospital.domain.hospital.controller.model.register.HospitalRegisterRequest;
import hospital.domain.hospital.controller.model.register.HospitalRegisterResponse;
import hospital.domain.hospital.converter.HospitalConverter;
import hospital.domain.hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class HospitalBusiness {

    private final HospitalService hospitalService;
    private final HospitalConverter hospitalConverter;
    private final MessageConverter messageConverter;

    public HospitalRegisterResponse register(HospitalRegisterRequest registerRequest, Long userId) {

        // 존재 유뮤 확인
        hospitalService.existsByHospitalWithThrow(registerRequest, userId);

        // Hospital 정보 저장
        HospitalEntity hospitalEntity = hospitalConverter.toEntity(registerRequest, userId);
        HospitalEntity savedHospitalEntity = hospitalService.register(hospitalEntity);

        // Message Queue -> Admin Module

        return hospitalConverter.toResponse(savedHospitalEntity);

    }

    public MessageResponse unregister(Long hospitalId, Long userId) {

        // 존재하지 않으면 예외
        hospitalService.notExistsByHospitalWithThrow(hospitalId, userId);

        HospitalEntity hospitalEntity = hospitalService.getHospitalBy(hospitalId,
            HospitalStatus.REGISTERED);
        hospitalService.unregister(hospitalEntity);

        return messageConverter.toResponse("병원이 삭제되었습니다.");
    }


}
