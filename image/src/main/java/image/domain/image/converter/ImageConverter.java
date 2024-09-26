package image.domain.image.converter;

import db.domain.image.ImageEntity;
import global.annotation.Converter;
import image.common.utils.ImageInfo;
import image.domain.image.controller.model.ImageRequest;
import image.domain.image.controller.model.ImageResponse;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Converter
@Slf4j
public class ImageConverter {

    @Value("${file.path}")
    private String uploadDir;

    public ImageEntity toEntity(ImageRequest imageRequest) {
        ImageInfo imageInfo = new ImageInfo(imageRequest, uploadDir);
        return ImageEntity.builder()
            .imageUrl(imageInfo.getImageUrl())
            .originalName(imageInfo.getOriginalFileName())
            .serverName(imageInfo.getServerName())
            .extension(imageInfo .getExtension())
            .registeredAt(LocalDateTime.now())
            .kind(imageRequest.getKind())
            .build();
    }

    public ImageResponse toResponse(ImageEntity imageEntity) {
        return ImageResponse.builder()
            .id(imageEntity.getId())
            .imageUrl(imageEntity.getImageUrl())
            .kind(imageEntity.getKind())
            .userId(imageEntity.getUserId())
            .build();
    }
}
