package user.converter;

import db.user.UserEntity;
import db.user.enums.UserRole;
import global.annotation.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import user.controller.model.register.UserRegisterRequest;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    public UserEntity toEntity(UserRegisterRequest userRegisterRequest) {
        return UserEntity.builder()
            .email(userRegisterRequest.getEmail())
            .password(passwordEncoder.encode(userRegisterRequest.getPassword()))
            .name(userRegisterRequest.getName())
            .birth(userRegisterRequest.getBirth())
            .address(userRegisterRequest.getAddress())
            .phone(userRegisterRequest.getPhone())
            .role(UserRole.BASIC)
            .build();
    }

}
