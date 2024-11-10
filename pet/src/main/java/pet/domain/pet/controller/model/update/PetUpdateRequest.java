package pet.domain.pet.controller.model.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetUpdateRequest {

    @NotNull
    private Long petId;

    @Pattern(regexp = "^[가-힣]{1,50}$", message = "한글 1자 이상 50자 이하로 입력해주세요.")
    private String name;

    private LocalDate birth;

    @NotBlank(message = "필수 입력 사항입니다.")
    @Size(max = 200, message = "최대 200자까지 입력 가능합니다.")
    private String address;

    //TODO 이미지 수정 추가!!
    private Long imageId;

}
