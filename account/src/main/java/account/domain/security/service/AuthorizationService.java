package account.domain.security.service;

import account.common.error.UserErrorCode;
import account.common.exception.user.UserNotFoundException;
import db.domain.user.UserEntity;
import db.domain.user.UserRepository;
import db.domain.user.enums.UserStatus;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<UserEntity> account = userRepository.findFirstByIdAndStatusOrderByIdDesc(
            Long.parseLong(userId), UserStatus.REGISTERED);

        return account.map(it -> User.builder().username(it.getEmail()).password(it.getPassword())
                .roles(it.getRole().toString()).build())
            .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

    }
}