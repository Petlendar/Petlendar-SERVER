package board.domain.user.service;

import db.domain.user.UserEntity;
import db.domain.user.UserRepository;
import db.domain.user.enums.UserStatus;
import global.errorcode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import board.common.exception.user.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getUserWithThrow(Long userId) {
        return userRepository.findFirstByIdAndStatusOrderByIdDesc(userId, UserStatus.REGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

}
