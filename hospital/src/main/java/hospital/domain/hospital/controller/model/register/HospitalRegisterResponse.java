package hospital.domain.hospital.controller.model.register;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalRegisterResponse {

    private Long hospitalId;

    private String name;

    private Long userId;

}
