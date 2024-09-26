package pet.domain.pet.controller.model.detail;

import db.domain.pet.enums.PetCategory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetDetailResponse {

    private Long petId;

    private String name;

    private LocalDate birth;

    private String address;

    private PetCategory category;

    private float weight;

    private Long userId;

}
