package db.domain.vaccination;

import db.common.BaseEntity;
import db.domain.vaccination.enums.VaccinationType;
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
@Table(name = "vaccination_record")
public class VaccinationEntity extends BaseEntity {

    private VaccinationType type;

    private LocalDateTime date; // 실제 접종등록 날짜

    private LocalDateTime registeredAt; // 해당 기록이 작성된 시간

    private Long petId;

    private Long userId;

    //TODO NULLABLE, 향후 HospitalModule 개발 시 변경
//    private Long hospitalId;


}
