package board.domain.board.controller.model.register;

import db.domain.pet.enums.PetCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardRegisterRequest {

    @NotBlank(message = "필수 입력 항목입니다.")
    @Size(min = 2, max = 100, message = "최소 2자, 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotBlank(message = "필수 입력 항목입니다.")
    @Size(min = 2, max = 1000, message = "최소 2자, 최대 1000자까지 입력 가능합니다.")
    private String content;

    @NotNull(message = "필수 입력 항목입니다.")
    private PetCategory category;

    private List<Long> imageIdList;

}
