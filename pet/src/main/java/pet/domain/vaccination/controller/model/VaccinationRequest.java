package pet.domain.vaccination.controller.model;

import db.domain.vaccination.enums.VaccinationType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationRequest {

    private Long petId;

    private VaccinationType type;

    private Long hospitalId;

    private LocalDateTime date;

}
