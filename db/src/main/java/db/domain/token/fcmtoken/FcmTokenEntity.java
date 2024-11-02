package db.domain.token.fcmtoken;

import db.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fcm_token")
public class FcmTokenEntity extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String fcmToken;

    @Column(nullable = false)
    private Long userId;

}
