package hospital.domain.hospital.controller.model.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalRegisterRequest {

    private String name;

    private String businessNumber;

    private String address;

    private String phone;

}
