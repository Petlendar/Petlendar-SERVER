package image.domain.image.service;

import db.domain.image.ImageEntity;
import db.domain.image.ImageRepository;
import image.common.error.ImageErrorCode;
import image.common.exception.image.ImageStorageException;
import image.common.utils.ImageUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final Path fileStorageLocation;

    public void uploadImage(MultipartFile file, ImageEntity imageEntity) {

        if (file.isEmpty()) {
            throw new ImageStorageException(ImageErrorCode.IMAGE_STORAGE_ERROR);
        }

        // 확장자 제한 가능 (단, 아직 구현하지 않음)
        String extension = ImageUtils.subStringExtension(imageEntity.getOriginalName());

        String fileName = ImageUtils.getCleanPath(imageEntity.getServerName() + extension);

        try {
            if (fileName.contains("..")) {
                throw new ImageStorageException(ImageErrorCode.IMAGE_STORAGE_ERROR,
                    "허용되지 않는 파일 이름입니다." + fileName);
            }

            // 파일 저장 경로 설정
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            // 이미 해당 경로에 파일이 존재하면 덮어씌움
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new ImageStorageException(ImageErrorCode.IMAGE_STORAGE_ERROR,
                "파일을 저장할 수 없습니다. 대상 파일 : " + fileName);
        }

    }

    public ImageEntity saveImageDataToDB(ImageEntity imageEntity, Long userId) {
        imageEntity.setUserId(userId);
        return imageRepository.save(imageEntity);
    }

}
