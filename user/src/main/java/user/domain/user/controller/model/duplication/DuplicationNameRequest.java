package user.domain.user.controller.model.duplication;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DuplicationNameRequest {

    @Pattern(
        regexp = "^[가-힣]{1,50}$",
        message = "이름은 한글 1자 이상 50자 이하로 입력해주세요."
    )
    private String name;

}
