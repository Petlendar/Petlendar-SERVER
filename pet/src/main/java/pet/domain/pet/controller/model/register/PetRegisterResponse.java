package pet.domain.pet.controller.model.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetRegisterResponse {

    private Long petId;

    private String name;

    private Long userId;

}
