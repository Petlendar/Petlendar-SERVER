package pet.domain.pet.controller.model.detail;


import db.domain.pet.enums.PetCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetListResponse {

    private Long petId;

    private String name;

    private PetCategory category;

}
