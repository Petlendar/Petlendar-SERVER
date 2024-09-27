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
        regexp = "^[가-힣a-zA-Z0-9]{2,50}$",
        message = "이름은 한글, 영어 또는 숫자로 2자 이상 50자 이하로 입력해주세요."
    )
    private String name;

}
