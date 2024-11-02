package pet.domain.vaccination.controller.model;

import db.domain.vaccination.enums.VaccinationType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationRequest {

    @NotNull
    private Long petId;

    @NotNull
    private VaccinationType type;

    private LocalDateTime date;

//    private Long hospitalId;

}
