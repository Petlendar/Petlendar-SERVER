package user.domain.user.controller;

import global.annotation.UserSession;
import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.common.response.MessageResponse;
import user.domain.user.business.UserBusiness;
import user.common.resolver.User;
import user.domain.user.controller.model.login.UserResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserBusiness userBusiness;

    @GetMapping()
    @Operation(summary = "[회원 정보 조회]")
    public Api<UserResponse> getUserInformation(@Parameter(hidden = true) @UserSession User user) {
        UserResponse response = userBusiness.getUserInformation(user.getId());
        return Api.OK(response);
    }

    @PostMapping("/unregister")
    @Operation(summary = "[회원 탈퇴]")
    public Api<MessageResponse> unregister(@Parameter(hidden = true) @UserSession User user) {
        MessageResponse response = userBusiness.unregister(user.getId());
        return Api.OK(response);
    }


}
