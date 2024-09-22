package user.controller.model.register;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    private String email;

    private String password;

    private String name;

    private LocalDate birth;

    private String address;

    private String phone;

}
