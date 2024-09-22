package user.business;

import db.user.UserEntity;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;
import user.common.response.MessageConverter;
import user.common.response.MessageResponse;
import user.controller.model.duplication.DuplicationEmailRequest;
import user.controller.model.duplication.DuplicationNameRequest;
import user.controller.model.login.UserLoginRequest;
import user.controller.model.login.UserResponse;
import user.controller.model.register.UserRegisterRequest;
import user.converter.UserConverter;
import user.security.jwt.business.TokenBusiness;
import user.security.jwt.model.TokenResponse;
import user.service.UserService;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final MessageConverter messageConverter;
    private final TokenBusiness tokenBusiness;

    public MessageResponse register(UserRegisterRequest userRegisterRequest) {

        userService.existsByEmailWithThrow(userRegisterRequest.getEmail());
        userService.existsByNameWithThrow(userRegisterRequest.getName());

        // 사용자 정보 저장
        UserEntity userEntity = userConverter.toEntity(userRegisterRequest);
        userService.register(userEntity);

        return messageConverter.toResponse("회원가입이 완료되었습니다.");
    }

    public MessageResponse duplicationEmailCheck(DuplicationEmailRequest duplicationEmailRequest) {
        userService.existsByEmailWithThrow(duplicationEmailRequest.getEmail());
        return messageConverter.toResponse("사용 가능한 아이디입니다.");
    }

    public MessageResponse duplicationNameCheck(DuplicationNameRequest duplicationNameRequest) {
        userService.existsByNameWithThrow(duplicationNameRequest.getName());
        return messageConverter.toResponse("사용 가능한 이름입니다.");
    }


    public TokenResponse login(UserLoginRequest userLoginRequest) {
        UserEntity userEntity = userService.login(userLoginRequest);
        return tokenBusiness.issueToken(userEntity);
    }

    public UserResponse getUserInformation(String email) {
        UserEntity userEntity = userService.getUserWithThrow(email);
        return userConverter. toResponse(userEntity);
    }
}
