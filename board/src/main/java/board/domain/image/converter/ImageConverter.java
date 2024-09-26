package board.domain.image.converter;

import board.domain.image.controller.model.ImageResponse;
import db.domain.image.ImageEntity;
import global.annotation.Converter;
import lombok.extern.slf4j.Slf4j;

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
