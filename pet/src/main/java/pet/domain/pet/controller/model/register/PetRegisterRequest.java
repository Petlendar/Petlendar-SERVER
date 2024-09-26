package pet.domain.pet.controller.model.register;

import db.domain.pet.enums.PetCategory;
import jakarta.validation.constraints.Null;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRegisterRequest {

    private String name;

    private LocalDate birth;

    private String address;

    private PetCategory category;

    private float weight;

    @Null
    private Long imageId;

}
