package pet.domain.pet.business;

import db.domain.pet.PetEntity;
import db.domain.pet.enums.PetStatus;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;
import pet.domain.pet.business.response.MessageConverter;
import pet.domain.pet.business.response.MessageResponse;
import pet.domain.pet.controller.model.register.PetRegisterRequest;
import pet.domain.pet.controller.model.register.PetRegisterResponse;
import pet.domain.pet.converter.PetConverter;
import pet.domain.pet.service.PetService;

@Business
@RequiredArgsConstructor
public class PetBusiness {

    private final PetService petService;
    private final PetConverter petConverter;
    private final MessageConverter messageConverter;

    public PetRegisterResponse register(PetRegisterRequest registerRequest, Long userId) {

        /**
         * Pet 의 name 과 birth, user id, status 를 기준으로 중복 확인
         * 등록된 Pet 이 존재할 경우 예외 발생
         */
        petService.existsByPetWithThrow(registerRequest, userId);

        // 저장
        PetEntity petEntity = petConverter.toEntity(registerRequest, userId);
        PetEntity savedEntity = petService.register(petEntity);

        return petConverter.toResponse(savedEntity);
    }

    public MessageResponse unregister(Long petId, Long userId) {

        // 존재하지 않으면 예외
        petService.notExistsByPetWithThrow(petId, userId);

        PetEntity petEntity = petService.getPetBy(petId, PetStatus.REGISTERED);
        petService.unregister(petEntity);
        return messageConverter.toResponse("반려동물이 삭제되었습니다.");
    }


}
