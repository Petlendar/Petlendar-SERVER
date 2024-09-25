package pet.domain.pet.controller.model.update;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetUpdateRequest {

    private Long petId;

    private String name;

    private LocalDate birth;

    private String address;

}
