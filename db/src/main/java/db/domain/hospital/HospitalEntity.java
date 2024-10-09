package db.domain.hospital;

import db.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
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
@Table(name = "hospital")
public class HospitalEntity extends BaseEntity {

    private String name;

    private String businessNumber;

    private String address;

    private String phone;

    private LocalDateTime registeredAt;

    private String userId;

}
