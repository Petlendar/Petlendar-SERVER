package db.domain.token.fcmtoken;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmTokenEntity, Long> {

    FcmTokenEntity findFirstByUserIdOrderByIdDesc(Long userId);

    void deleteAllByFcmTokenIn(List<String> failedTokens);

}
