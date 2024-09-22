package user.converter;

import db.user.UserEntity;
import db.user.enums.UserRole;
import global.annotation.Converter;
import user.controller.model.register.UserRegisterRequest;

@Converter
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest userRegisterRequest) {
        return UserEntity.builder()
            .email(userRegisterRequest.getEmail())
            .password(userRegisterRequest.getPassword())
            .name(userRegisterRequest.getName())
            .birth(userRegisterRequest.getBirth())
            .address(userRegisterRequest.getAddress())
            .phone(userRegisterRequest.getPhone())
            .role(UserRole.BASIC)
            .build();
    }

}
