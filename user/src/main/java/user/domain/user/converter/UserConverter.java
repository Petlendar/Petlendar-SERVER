package user.domain.user.converter;

import db.domain.user.UserEntity;
import db.domain.user.enums.UserRole;
import global.annotation.Converter;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import user.domain.user.controller.model.login.UserResponse;
import user.domain.user.controller.model.register.UserRegisterRequest;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest userRegisterRequest) {
        return UserEntity.builder()
            .email(userRegisterRequest.getEmail())
            .password(BCrypt.hashpw(userRegisterRequest.getPassword(), BCrypt.gensalt()))
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
