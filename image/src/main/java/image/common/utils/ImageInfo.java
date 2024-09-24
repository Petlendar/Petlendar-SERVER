package image.common.utils;

import image.domain.image.controller.model.ImageRequest;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageInfo {

    private String uploadDir;
    private String originalFileName;
    private String serverName;
    private String extension;
    private String fileName;
    private String imageUrl;

    public ImageInfo(ImageRequest request, String uploadDir) {
        this.uploadDir = uploadDir;
        this.originalFileName = request.getFile().getOriginalFilename();
        this.serverName = UUID.randomUUID().toString();
        this.extension = ImageUtils.subStringExtension(
            Objects.requireNonNull(this.originalFileName));
        this.fileName = StringUtils.cleanPath(this.serverName + this.extension);
        this.imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .scheme("http")
            .path(uploadDir + fileName)
            .toUriString();
    }

}
