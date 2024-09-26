package user.domain.user.business;

import db.domain.user.UserEntity;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;
import user.common.response.MessageConverter;
import user.common.response.MessageResponse;
import user.domain.user.controller.model.duplication.DuplicationEmailRequest;
import user.domain.user.controller.model.duplication.DuplicationNameRequest;
import user.domain.user.controller.model.login.UserLoginRequest;
import user.domain.user.controller.model.login.UserResponse;
import user.domain.user.controller.model.register.UserRegisterRequest;
import user.domain.user.converter.UserConverter;
import user.domain.jwt.business.TokenBusiness;
import user.domain.jwt.model.TokenResponse;
import user.domain.user.service.UserService;

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

    public UserResponse getUserInformation(Long userId) {
        UserEntity userEntity = userService.getUserWithThrow(userId);
        return userConverter.toResponse(userEntity);
    }

    public MessageResponse unregister(Long userId) {
        userService.unregister(userId);
        return messageConverter.toResponse("회원탈퇴가 완료되었습니다.");
    }

}
