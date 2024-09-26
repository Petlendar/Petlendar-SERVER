package pet.domain.image.service;

import db.domain.image.ImageEntity;
import db.domain.image.ImageRepository;
import db.domain.pet.PetEntity;
import global.errorcode.ImageErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.common.exception.image.ImageNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    /**
     * 이미지가 자신이 업로드한 이미지를 판단 및 등록
     */
    public void linkImageToPet(Long imageId, PetEntity petEntity) {
        if (imageId != null) {
            imageRepository.findFirstByIdAndUserId(imageId, petEntity.getUserId())
                .map(imageEntity -> {
                    imageEntity.setPetId(petEntity.getId());
                    return imageRepository.save(imageEntity);
                }).orElseThrow(() -> new ImageNotFoundException(ImageErrorCode.IMAGE_NOT_FOUND));
        }
    }

    public ImageEntity getImageOrNullBy(Long petId) {
        return imageRepository.findFirstByPetIdOrderByIdDesc(petId).orElse(null);
    }

}
