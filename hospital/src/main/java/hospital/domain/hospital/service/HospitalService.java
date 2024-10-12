package hospital.domain.hospital.service;

import db.domain.hospital.HospitalEntity;
import db.domain.hospital.HospitalRepository;
import db.domain.hospital.enums.HospitalStatus;
import db.domain.pet.enums.PetStatus;
import hospital.domain.hospital.controller.model.register.HospitalRegisterRequest;
import java.time.LocalDateTime;
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

    public void notExistsByHospitalWithThrow(Long hospitalId, Long userId) {
        Boolean existsByHospital = hospitalRepository.existsByIdAndUserIdAndStatus(hospitalId, userId,
            HospitalStatus.REGISTERED);
        if (!existsByHospital) {
            throw new RuntimeException("존재하지 않는 병원 정보입니다.");
        }
    }

    public HospitalEntity getHospitalBy(Long hospitalId, HospitalStatus status) {
        return hospitalRepository.findFirstByIdAndStatusOrderByIdDesc(hospitalId, status)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 병원 정보입니다."));
    }

    public void unregister(HospitalEntity hospitalEntity) {
        hospitalEntity.setStatus(HospitalStatus.UNREGISTERED);
        hospitalEntity.setUnregisteredAt(LocalDateTime.now());
        hospitalRepository.save(hospitalEntity);
    }

}
