package user.controller;

import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.business.UserBusiness;
import user.common.response.MessageResponse;
import user.controller.model.duplication.DuplicationEmailRequest;
import user.controller.model.duplication.DuplicationNameRequest;
import user.controller.model.register.UserRegisterRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping()
    @Operation(summary = "[회원가입]")
    public Api<MessageResponse> register(
        @RequestBody Api<UserRegisterRequest> userRegisterRequest
    ) {
        MessageResponse response = userBusiness.register(userRegisterRequest.getBody());
        return Api.OK(response);
    }

    @PostMapping("/duplication/email")
    @Operation(summary = "[email 중복 확인]")
    public Api<MessageResponse> duplicationEmailCheck(
        @RequestBody Api<DuplicationEmailRequest> duplicationEmailRequest
    ) {
        MessageResponse response = userBusiness.duplicationEmailCheck(
            duplicationEmailRequest.getBody());
        return Api.OK(response);
    }

    @PostMapping("/duplication/name")
    @Operation(summary = "[name 중복 확인]")
    public Api<MessageResponse> duplicationNameCheck(
        @RequestBody Api<DuplicationNameRequest> duplicationNameRequest
    ) {
        MessageResponse response = userBusiness.duplicationNameCheck(
            duplicationNameRequest.getBody());
        return Api.OK(response);
    }


}
