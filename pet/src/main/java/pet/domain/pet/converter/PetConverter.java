package pet.domain.pet.converter;

import db.domain.pet.PetEntity;
import db.domain.pet.enums.PetStatus;
import global.annotation.Converter;
import java.time.LocalDateTime;
import java.util.List;
import pet.domain.image.controller.model.ImageResponse;
import pet.domain.pet.controller.model.detail.PetDetailResponse;
import pet.domain.pet.controller.model.detail.PetListResponse;
import pet.domain.pet.controller.model.register.PetRegisterRequest;
import pet.domain.pet.controller.model.register.PetRegisterResponse;

@Converter
public class PetConverter {

    public PetEntity toEntity(PetRegisterRequest request, Long userId) {
        return PetEntity.builder()
            .name(request.getName())
            .birth(request.getBirth())
            .address(request.getAddress())
            .category(request.getCategory())
            .weight(request.getWeight())
            .registeredAt(LocalDateTime.now())
            .status(PetStatus.REGISTERED)
            .userId(userId)
            .build();
    }

    public PetRegisterResponse toResponse(PetEntity petEntity) {
        return PetRegisterResponse.builder()
            .petId(petEntity.getId())
            .name(petEntity.getName())
            .userId(petEntity.getUserId())
            .build();
    }

    public PetDetailResponse toDetailResponse(PetEntity petEntity, ImageResponse imageResponse) {
        return PetDetailResponse.builder()
            .petId(petEntity.getId())
            .name(petEntity.getName())
            .birth(petEntity.getBirth())
            .address(petEntity.getAddress())
            .category(petEntity.getCategory())
            .weight(petEntity.getWeight())
            .userId(petEntity.getUserId())
            .petImage(imageResponse)
            .build();
    }

    public PetListResponse toListResponse(PetEntity petEntity, ImageResponse imageResponse) {
        return PetListResponse.builder()
            .petId(petEntity.getId())
            .name(petEntity.getName())
            .category(petEntity.getCategory())
            .petImage(imageResponse)
            .build();
    }
}
