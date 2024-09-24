package pet.domain.pet.service;

import db.domain.pet.PetEntity;
import db.domain.pet.PetRepository;
import db.domain.pet.enums.PetStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.domain.pet.controller.model.PetRegisterRequest;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public void existsByPetWithThrow(PetRegisterRequest request, Long userId) {
        Boolean existsByPet = petRepository.existsByNameAndBirthAndUserIdAndStatus(request.getName(),
            request.getBirth(), userId, PetStatus.REGISTERED);
        if(existsByPet) {
            throw new RuntimeException("이미 등록된 반려동물입니다.");
        }
    }

    public PetEntity register(PetEntity petEntity) {
        return petRepository.save(petEntity);
    }

}
