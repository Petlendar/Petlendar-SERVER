package pet.domain.image.converter;

import db.domain.image.ImageEntity;
import global.annotation.Converter;
import lombok.extern.slf4j.Slf4j;
import pet.domain.image.controller.model.ImageResponse;

@Converter
@Slf4j
public class ImageConverter {

    public ImageResponse toResponse(ImageEntity imageEntity) {
        return ImageResponse.builder()
            .id(imageEntity.getId())
            .imageUrl(imageEntity.getImageUrl())
            .kind(imageEntity.getKind())
            .userId(imageEntity.getUserId())
            .build();
    }

}
