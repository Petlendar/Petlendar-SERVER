package scheduler.domain.pet.service;

import db.domain.pet.PetEntity;
import db.domain.pet.PetRepository;
import db.domain.pet.enums.PetStatus;
import global.errorcode.PetErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import scheduler.common.exception.pet.PetNotFoundException;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public List<PetEntity> getPetListBy(Long userId, PetStatus status) {
        List<PetEntity> petEntityList = petRepository.findAllByUserIdAndStatusOrderByIdAsc(
            userId, status);
        if (petEntityList.isEmpty()) {
            throw new PetNotFoundException(PetErrorCode.PET_NOT_FOUND);
        }
        return petEntityList;
    }

    public void notExistsByPetWithThrow(Long petId, Long userId) {
        Boolean existsByPet = petRepository.existsByIdAndUserIdAndStatus(petId, userId,
            PetStatus.REGISTERED);
        if (!existsByPet) {
            throw new PetNotFoundException(PetErrorCode.PET_NOT_FOUND);
        }
    }

    public List<PetEntity> getAllPetWithThrowBy(PetStatus petStatus) {
        List<PetEntity> petEntityList = petRepository.findAllByStatus(petStatus);
        if (petEntityList.isEmpty()) {
            throw new PetNotFoundException(PetErrorCode.PET_NOT_FOUND);
        }
        return petEntityList;
    }

}
