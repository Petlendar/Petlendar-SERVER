package db.domain.vaccination;

import db.domain.vaccination.enums.VaccinationType;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<VaccinationEntity, Long> {

    Boolean existsByTypeAndDateAndUserIdAndPetId(VaccinationType type, LocalDateTime date, Long userId, Long petId);

    Boolean existsByPetId(Long petId);

    List<VaccinationEntity> findAllByPetIdOrderByIdAsc(Long petId);

}
