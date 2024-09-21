package user.security.service;

import db.user.UserEntity;
import db.user.UserRepository;
import db.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import user.common.error.UserErrorCode;
import user.common.exception.user.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findFirstByIdAndStatusOrderByIdDesc(
                Long.parseLong(userId), UserStatus.REGISTERED)
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        return User.builder()
            .username(userEntity.getEmail())
            .password(userEntity.getPassword())
            .roles(userEntity.getRole().toString())
            .build();
    }

}
