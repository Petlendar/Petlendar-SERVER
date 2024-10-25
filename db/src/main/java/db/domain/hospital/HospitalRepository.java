package db.domain.hospital;

import db.domain.hospital.enums.HospitalStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    Boolean existsByNameAndUserIdAndStatus(String name, Long userId, HospitalStatus status);

    Boolean existsByIdAndUserIdAndStatus(Long hospitalId, Long userId, HospitalStatus hospitalStatus);

    Optional<HospitalEntity> findFirstByIdAndStatusOrderByIdDesc(Long hospitalId, HospitalStatus status);

}
