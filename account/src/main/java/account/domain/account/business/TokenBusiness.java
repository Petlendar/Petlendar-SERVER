package account.domain.account.business;

import account.common.error.UserErrorCode;
import account.common.exception.user.UserNotFoundException;
import account.domain.account.controller.model.TokenValidationResponse;
import account.domain.account.model.TokenDto;
import account.domain.account.service.TokenService;
import db.domain.user.UserEntity;
import db.domain.user.UserRepository;
import db.domain.user.enums.UserStatus;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Business
@RequiredArgsConstructor
@Slf4j
public class TokenBusiness {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    public TokenValidationResponse tokenValidation(TokenDto tokenDto) {
        Long userId = tokenService.validationToken(tokenDto.getToken().substring(7));

        UserEntity userEntity = userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
        return TokenValidationResponse.builder()
            .userId(userEntity.getId())
            .email(userEntity.getEmail())
            .role(userEntity.getRole())
            .build();
    }
}
