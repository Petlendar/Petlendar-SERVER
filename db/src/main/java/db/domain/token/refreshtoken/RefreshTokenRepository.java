package db.domain.token.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    void deleteByUserId(Long userId);

    RefreshTokenEntity findFirstByUserIdOrderByUserId(Long userId);

}
