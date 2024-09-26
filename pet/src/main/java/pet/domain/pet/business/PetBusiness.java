package pet.domain.pet.business;

import db.domain.pet.PetEntity;
import db.domain.pet.enums.PetStatus;
import global.annotation.Business;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pet.common.response.MessageConverter;
import pet.common.response.MessageResponse;
import pet.domain.pet.controller.model.detail.PetListResponse;
import pet.domain.pet.controller.model.detail.PetDetailResponse;
import pet.domain.pet.controller.model.register.PetRegisterRequest;
import pet.domain.pet.controller.model.register.PetRegisterResponse;
import pet.domain.pet.controller.model.update.PetUpdateRequest;
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

    public MessageResponse update(PetUpdateRequest petUpdateRequest, Long userId) {

        // 존재하지 않으면 예외
        petService.notExistsByPetWithThrow(petUpdateRequest.getPetId(), userId);

        PetEntity petEntity = petService.getPetBy(petUpdateRequest.getPetId(), PetStatus.REGISTERED);
        petService.update(petEntity, petUpdateRequest);
        return messageConverter.toResponse("반려동물 정보가 수정되었습니다.");
    }

    public PetDetailResponse getPetBy(Long petId, Long userId) {

        // 존재하지 않으면 예외
        petService.notExistsByPetWithThrow(petId, userId);

        PetEntity petEntity = petService.getPetBy(petId, PetStatus.REGISTERED);
        return petConverter.toDetailResponse(petEntity);
    }

    public List<PetListResponse> getPetListBy(Long userId) {

        // userId 로 REGISTERED 인 petEntity 조회
        List<PetEntity> petEntityList = petService.getPetListBy(userId, PetStatus.REGISTERED);

        return petEntityList.stream().map(petEntity ->
            petConverter.toListResponse(petEntity)
        ).toList();
    }

}
