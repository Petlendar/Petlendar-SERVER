package db.domain.pet;

import db.domain.pet.enums.PetStatus;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long> {

    Boolean existsByNameAndBirthAndUserIdAndStatus(String name, LocalDate birth, Long userId, PetStatus status);
}
