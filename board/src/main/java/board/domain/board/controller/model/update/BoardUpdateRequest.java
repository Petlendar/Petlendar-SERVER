package board.domain.board.controller.model.update;

import db.domain.pet.enums.PetCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {

    @NotNull(message = "필수 입력 항목입니다.")
    private Long boardId;

    @NotBlank(message = "필수 입력 항목입니다.")
    @Size(min = 2, max = 100, message = "최소 2자, 최대 100자까지 입력 가능합니다.")
    private String title;

    @NotBlank(message = "필수 입력 항목입니다.")
    @Size(min = 2, max = 1000, message = "최소 2자, 최대 1000자까지 입력 가능합니다.")
    private String content;

    @NotNull
    private PetCategory category;

}
