package db.user;

import db.user.enums.UserStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus userStatus);

    boolean existsByEmail(String email);

    boolean existsByName(String name);

    Optional<UserEntity> findFirstByEmailAndStatusNotOrderByEmailDesc(String email,
        UserStatus userStatus);
}
