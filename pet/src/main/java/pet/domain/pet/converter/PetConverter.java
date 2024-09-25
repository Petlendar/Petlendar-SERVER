package pet.domain.pet.converter;

import db.domain.pet.PetEntity;
import db.domain.pet.enums.PetStatus;
import global.annotation.Converter;
import java.time.LocalDateTime;
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

    public PetRegisterResponse toResponse(PetEntity savedEntity) {
        return PetRegisterResponse.builder()
            .petId(savedEntity.getId())
            .name(savedEntity.getName())
            .userId(savedEntity.getUserId())
            .build();
    }
}
