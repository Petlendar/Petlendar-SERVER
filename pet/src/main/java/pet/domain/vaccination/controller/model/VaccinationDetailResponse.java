package pet.domain.vaccination.controller.model;

import db.domain.vaccination.enums.VaccinationType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationDetailResponse {

    private VaccinationType type;

    private LocalDateTime date; // 실제 접종등록 날짜

    private LocalDateTime registeredAt; // 해당 기록이 작성된 시간

    private Long petId;

    private Long userId;

    //TODO NULLABLE, 향후 HospitalModule 개발 시 변경
    private Long hospitalId;

}
