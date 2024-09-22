package user.service;

import db.user.UserEntity;
import db.user.UserRepository;
import db.user.enums.UserStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import user.common.error.UserErrorCode;
import user.common.exception.user.ExistUserEmailException;
import user.common.exception.user.ExistUserNameException;
import user.common.exception.user.LoginFailException;
import user.common.exception.user.UserNotFoundException;
import user.controller.model.login.UserLoginRequest;

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

    public UserEntity getUserWithThrow(String email) {
        return userRepository.findFirstByEmailAndStatusOrderByIdDesc(email, UserStatus.REGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }
}
