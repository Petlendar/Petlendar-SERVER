package pet.domain.pet.service;

import db.domain.pet.PetEntity;
import db.domain.pet.PetRepository;
import db.domain.pet.enums.PetStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.common.error.PetErrorCode;
import pet.common.exception.pet.ExistsPetException;
import pet.common.exception.pet.PetNotFoundException;
import pet.domain.pet.controller.model.register.PetRegisterRequest;
import pet.domain.pet.controller.model.update.PetUpdateRequest;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public PetEntity register(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

    public PetEntity getPetBy(Long petId, PetStatus status) {
        return petRepository.findFirstByIdAndStatusOrderByIdDesc(petId, status)
            .orElseThrow(() -> new PetNotFoundException(PetErrorCode.PET_NOT_FOUND));
    }

    public void unregister(PetEntity petEntity) {
        petEntity.setStatus(PetStatus.UNREGISTERED);
        petEntity.setUnregisteredAt(LocalDateTime.now());
        petRepository.save(petEntity);
    }

    public void existsByPetWithThrow(PetRegisterRequest request, Long userId) {
        Boolean existsByPet = petRepository.existsByNameAndBirthAndUserIdAndStatus(
            request.getName(), request.getBirth(), userId, PetStatus.REGISTERED);
        if (existsByPet) {
            throw new ExistsPetException(PetErrorCode.EXISTS_PET);
        }
    }

    public void notExistsByPetWithThrow(Long petId, Long userId) {
        Boolean existsByPet = petRepository.existsByIdAndUserIdAndStatus(petId, userId,
            PetStatus.REGISTERED);
        if (!existsByPet) {
            throw new RuntimeException("존재하지 않는 반려동물입니다."); // TODO 예외처리
        }
    }

    public void update(PetEntity petEntity, PetUpdateRequest petUpdateRequest) {
        petEntity.setName(petUpdateRequest.getName());
        petEntity.setBirth(petUpdateRequest.getBirth());
        petEntity.setAddress(petUpdateRequest.getAddress());
        petRepository.save(petEntity);
    }

}
