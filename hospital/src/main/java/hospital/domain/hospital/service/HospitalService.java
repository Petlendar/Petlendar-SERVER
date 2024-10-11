package hospital.domain.hospital.service;

import db.domain.hospital.HospitalEntity;
import db.domain.hospital.HospitalRepository;
import db.domain.hospital.enums.HospitalStatus;
import db.domain.pet.enums.PetStatus;
import global.errorcode.PetErrorCode;
import hospital.domain.hospital.controller.model.register.HospitalRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public void existsByHospitalWithThrow(HospitalRegisterRequest request, Long userId) {
        Boolean existsByHospital = hospitalRepository.existsByNameAndUserIdAndStatus(
            request.getName(), userId, HospitalStatus.REGISTERED);
        if (existsByHospital) {
            throw new RuntimeException("이미 등록된 병원입니다.");
        }
    }

    public HospitalEntity register(HospitalEntity hospitalEntity) {
        return hospitalRepository.save(hospitalEntity);
    }
}
