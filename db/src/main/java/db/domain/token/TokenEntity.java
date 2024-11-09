package db.domain.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    private Long userId;

    private String refreshToken;

    private String fcmToken;

}
