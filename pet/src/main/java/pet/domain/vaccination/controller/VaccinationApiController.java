package pet.domain.vaccination.controller;

import global.annotation.UserSession;
import global.api.Api;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.common.resolver.User;
import pet.domain.vaccination.business.VaccinationBusiness;
import pet.domain.vaccination.controller.model.VaccinationDetailResponse;
import pet.domain.vaccination.controller.model.VaccinationRequest;
import pet.domain.vaccination.controller.model.VaccinationResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vaccination")
public class VaccinationApiController {

    private final VaccinationBusiness vaccinationBusiness;

    @PostMapping()
    public Api<VaccinationResponse> register(
        @RequestBody @Valid Api<VaccinationRequest> registerRequest,
        @Parameter(hidden = true) @UserSession User user
    ) {
        VaccinationResponse response = vaccinationBusiness.register(registerRequest.getBody(),
            user.getId());
        return Api.OK(response);
    }
}
