package image.domain.image.controller;

import global.annotation.UserSession;
import global.api.Api;
import image.domain.image.business.ImageBusiness;
import image.domain.image.controller.model.ImageRequest;
import image.domain.image.controller.model.ImageResponse;
import image.domain.user.controller.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageApiController {

    private final ImageBusiness imageBusiness;

    @PostMapping()
    @Operation(summary = "[단일 이미지 Upload]")
    public Api<ImageResponse> upload(ImageRequest imageRequest, @UserSession User user) {
        ImageResponse response = imageBusiness.uploadImage(imageRequest, user.getId());
        return Api.OK(response);
    }

}