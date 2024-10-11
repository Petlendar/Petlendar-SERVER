package hospital.domain.hospital.controller;

import global.annotation.UserSession;
import global.api.Api;
import hospital.common.resolver.User;
import hospital.domain.hospital.business.HospitalBusiness;
import hospital.domain.hospital.controller.model.register.HospitalRegisterRequest;
import hospital.domain.hospital.controller.model.register.HospitalRegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospital")
public class HospitalApiController {

    private final HospitalBusiness hospitalBusiness;

    @PostMapping()
    public Api<HospitalRegisterResponse> register(
        @RequestBody @Valid Api<HospitalRegisterRequest> registerRequest,
        @UserSession User user
    )
    {
        HospitalRegisterResponse response = hospitalBusiness.register(registerRequest.getBody(), user.getId());
        return Api.OK(response);
    }

}
