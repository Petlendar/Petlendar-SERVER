package db.domain.token.fcmtoken;

import db.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "fcm_token")
public class FcmTokenEntity extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String fcmToken;

    @Column(nullable = false)
    private Long userId;

}
