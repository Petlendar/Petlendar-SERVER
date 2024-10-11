package db.domain.hospital;

import db.domain.hospital.enums.HospitalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    Boolean existsByNameAndUserIdAndStatus(String name, Long userId, HospitalStatus status);
}
