package hospital.domain.hospital.business;

import db.domain.hospital.HospitalEntity;
import global.annotation.Business;
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

    public HospitalRegisterResponse register(HospitalRegisterRequest registerRequest, Long userId) {

        // 존재 유뮤 확인
        hospitalService.existsByHospitalWithThrow(registerRequest, userId);

        // Hospital 정보 저장
        HospitalEntity hospitalEntity = hospitalConverter.toEntity(registerRequest, userId);
        HospitalEntity savedHospitalEntity = hospitalService.register(hospitalEntity);

        // Message Queue -> Admin Module

        return hospitalConverter.toResponse(savedHospitalEntity);

    }
}
