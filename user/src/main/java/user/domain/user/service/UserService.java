package user.domain.user.service;

import db.domain.user.UserEntity;
import db.domain.user.UserRepository;
import db.domain.user.enums.UserStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import user.common.error.UserErrorCode;
import user.common.exception.user.ExistUserEmailException;
import user.common.exception.user.ExistUserNameException;
import user.common.exception.user.LoginFailException;
import user.common.exception.user.UserNotFoundException;
import user.common.exception.user.UserUnregisterException;
import user.domain.user.controller.model.login.UserLoginRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void register(UserEntity userEntity) {
        userEntity.setStatus(UserStatus.REGISTERED);
        userEntity.setRegisteredAt(LocalDateTime.now());
        userRepository.save(userEntity);
    }

    public void existsByNameWithThrow(String name) {
        boolean existsByName = userRepository.existsByName(name);
        if (existsByName) {
            throw new ExistUserNameException(UserErrorCode.EXISTS_USER_NAME);
        }
    }

    public void existsByEmailWithThrow(String email) {
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new ExistUserEmailException(UserErrorCode.EXISTS_USER_EMAIL);
        }
    }

    public UserEntity login(UserLoginRequest userLoginRequest) {

        // UNREGISTERED 가 아닌 UserEntity 반환
        UserEntity userEntity = userRepository.findFirstByEmailAndStatusNotOrderByEmailDesc(
                userLoginRequest.getEmail(), UserStatus.UNREGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        if (BCrypt.checkpw(userLoginRequest.getPassword(), userEntity.getPassword())) {
            userEntity.setLastLoginAt(LocalDateTime.now());
            userRepository.save(userEntity);
            return userEntity;
        }

        throw new LoginFailException(UserErrorCode.LOGIN_FAIL);
    }

    public UserEntity getUserWithThrow(Long userId) {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

    public void unregister(Long userId) {
        UserEntity userEntity = userRepository.findFirstByIdAndStatusOrderByIdDesc(userId,
                UserStatus.REGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
        userEntity.setStatus(UserStatus.UNREGISTERED);
        userEntity.setUnregisteredAt(LocalDateTime.now());
        UserEntity unRegisterdUserEntity = userRepository.save(userEntity);
        if (!unRegisterdUserEntity.getStatus().equals(UserStatus.UNREGISTERED)) {
            throw new UserUnregisterException(UserErrorCode.USER_UNREGISTER_FAIL);
        }
    }

}
