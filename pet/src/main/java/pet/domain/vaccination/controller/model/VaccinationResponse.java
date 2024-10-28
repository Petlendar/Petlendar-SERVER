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
public class VaccinationResponse {

    private Long vaccinationRecordId;

    private VaccinationType type;

    private LocalDateTime date;

    private Long petId;

}
