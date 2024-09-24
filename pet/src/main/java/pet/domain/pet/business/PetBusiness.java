package pet.domain.pet.business;

import db.domain.pet.PetEntity;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;
import pet.domain.pet.controller.model.PetRegisterRequest;
import pet.domain.pet.controller.model.PetRegisterResponse;
import pet.domain.pet.converter.PetConverter;
import pet.domain.pet.service.PetService;

@Business
@RequiredArgsConstructor
public class PetBusiness {

    private final PetService petService;
    private final PetConverter petConverter;

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
}
