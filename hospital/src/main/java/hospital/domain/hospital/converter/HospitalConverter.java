package hospital.domain.hospital.converter;

import db.domain.hospital.HospitalEntity;
import db.domain.hospital.enums.HospitalStatus;
import global.annotation.Converter;
import hospital.domain.hospital.controller.model.register.HospitalRegisterRequest;
import hospital.domain.hospital.controller.model.register.HospitalRegisterResponse;
import java.time.LocalDateTime;

@Converter
public class HospitalConverter {

    public HospitalEntity toEntity(HospitalRegisterRequest registerRequest, Long userId) {
        return HospitalEntity.builder()
            .name(registerRequest.getName())
            .businessNumber(registerRequest.getBusinessNumber())
            .address(registerRequest.getAddress())
            .phone(registerRequest.getPhone())
            .status(HospitalStatus.REGISTERED)
            .userId(userId)
            .registeredAt(LocalDateTime.now())
            .build();
    }

    public HospitalRegisterResponse toResponse(HospitalEntity hospitalEntity) {
        return HospitalRegisterResponse.builder()
            .hospitalId(hospitalEntity.getId())
            .name(hospitalEntity.getName())
            .userId(hospitalEntity.getUserId())
            .build();
    }

}
