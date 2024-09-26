package image.domain.image.business;

import db.domain.image.ImageEntity;
import global.annotation.Business;
import image.domain.image.controller.model.ImageRequest;
import image.domain.image.controller.model.ImageResponse;
import image.domain.image.converter.ImageConverter;
import image.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Business
@Slf4j
@RequiredArgsConstructor
public class ImageBusiness {

    private final ImageService imageService;
    private final ImageConverter imageConverter;

    public ImageResponse uploadImage(ImageRequest imageRequest, Long userId) {
        ImageEntity imageEntity = imageConverter.toEntity(imageRequest); // 서버에 저장하는 imageEntity

        imageService.uploadImage(imageRequest.getFile(), imageEntity); // 서버에 파일 업로드

        ImageEntity savedImageEntity = imageService.saveImageDataToDB(imageEntity, userId);

        return imageConverter.toResponse(savedImageEntity);
    }

}
