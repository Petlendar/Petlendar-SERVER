package user.domain.user.controller;

import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.domain.user.business.UserBusiness;
import user.common.response.MessageResponse;
import user.domain.user.controller.model.duplication.DuplicationEmailRequest;
import user.domain.user.controller.model.duplication.DuplicationNameRequest;
import user.domain.user.controller.model.login.UserLoginRequest;
import user.domain.user.controller.model.register.UserRegisterRequest;
import user.domain.jwt.model.TokenResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    @PostMapping()
    @Operation(summary = "[회원가입]")
    public Api<MessageResponse> register(
        @RequestBody @Valid Api<UserRegisterRequest> userRegisterRequest
    ) {
        MessageResponse response = userBusiness.register(userRegisterRequest.getBody());
        return Api.OK(response);
    }

    @PostMapping("/duplication/email")
    @Operation(summary = "[email 중복 확인]")
    public Api<MessageResponse> duplicationEmailCheck(
        @RequestBody @Valid Api<DuplicationEmailRequest> duplicationEmailRequest
    ) {
        MessageResponse response = userBusiness.duplicationEmailCheck(
            duplicationEmailRequest.getBody());
        return Api.OK(response);
    }

    @PostMapping("/duplication/name")
    @Operation(summary = "[name 중복 확인]")
    public Api<MessageResponse> duplicationNameCheck(
        @RequestBody @Valid Api<DuplicationNameRequest> duplicationNameRequest
    ) {
        MessageResponse response = userBusiness.duplicationNameCheck(
            duplicationNameRequest.getBody());
        return Api.OK(response);
    }

    @PostMapping("/login")
    @Operation(summary = "[로그인]")
    public Api<TokenResponse> login(@RequestBody @Valid Api<UserLoginRequest> userLoginRequest) {
        TokenResponse response = userBusiness.login(userLoginRequest.getBody());
        return Api.OK(response);
    }

}
