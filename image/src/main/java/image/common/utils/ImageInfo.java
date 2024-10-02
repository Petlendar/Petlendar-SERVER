package image.common.utils;

import image.domain.image.controller.model.ImageRequest;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class ImageInfo {

    private String uploadDir;
    private String originalFileName;
    private String serverName;
    private String extension;
    private String fileName;
    private String imageUrl;

    public ImageInfo(ImageRequest request, String uploadDir, String baseAddress) {
        this.uploadDir = uploadDir;
        this.originalFileName = request.getFile().getOriginalFilename();
        this.serverName = UUID.randomUUID().toString();
        this.extension = ImageUtils.subStringExtension(
            Objects.requireNonNull(this.originalFileName));
        this.fileName = StringUtils.cleanPath(this.serverName + this.extension);

        log.info("baseAddress : {}", baseAddress);
        this.imageUrl = ServletUriComponentsBuilder.fromHttpUrl("http://" + baseAddress)
            .port(80)
            .path("/images/" + fileName)
            .toUriString();
    }

}
