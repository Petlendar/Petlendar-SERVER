package user.service;

import db.user.UserEntity;
import db.user.UserRepository;
import db.user.enums.UserStatus;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("이미 존재하는 닉네임입니다.");
        }
    }

    public void existsByEmailWithThrow(String email) {
        boolean existsByEmail = userRepository.existsByEmail(email);
        if (existsByEmail) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }
    }

}
