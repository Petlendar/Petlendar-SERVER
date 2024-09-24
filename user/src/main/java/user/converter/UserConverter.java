package user.converter;

import db.domain.user.UserEntity;
import db.domain.user.enums.UserRole;
import global.annotation.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import user.controller.model.login.UserResponse;
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

    public UserResponse toResponse(UserEntity userEntity) {
        return UserResponse.builder()
            .id(userEntity.getId())
            .email(userEntity.getEmail())
            .name(userEntity.getName())
            .birth(userEntity.getBirth())
            .address(userEntity.getAddress())
            .phone(userEntity.getPhone())
            .role(userEntity.getRole())
            .build();
    }

}
