package pet.domain.pet.controller.model.register;

import db.domain.pet.enums.PetCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetRegisterRequest {

    @Pattern(regexp = "^[가-힣]{1,50}$", message = "한글 1자 이상 50자 이하로 입력해주세요.")
    private String name;

    private LocalDate birth;

    @NotBlank(message = "필수 입력 사항입니다.")
    @Size(max = 200, message = "최대 200자까지 입력 가능합니다.")
    private String address;

    @NotNull(message = "필수 입력 항목입니다.")
    private PetCategory category;

    @DecimalMin(value = "0.0", inclusive = false, message = "0.1 이상이어야 합니다.")
    private float weight;

    private Long imageId;

}
