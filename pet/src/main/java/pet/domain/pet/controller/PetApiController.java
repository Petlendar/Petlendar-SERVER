package pet.domain.pet.controller;

import global.annotation.UserSession;
import global.api.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.domain.pet.business.PetBusiness;
import pet.domain.pet.controller.model.PetRegisterRequest;
import pet.domain.pet.controller.model.PetRegisterResponse;
import pet.domain.user.controller.model.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pet")
public class PetApiController {

    private final PetBusiness petBusiness;

    @PostMapping()
    public Api<PetRegisterResponse> register(
        @RequestBody Api<PetRegisterRequest> registerRequest,
        @UserSession User user
    ) {
        PetRegisterResponse response = petBusiness.register(registerRequest.getBody(), user.getId());
        return Api.OK(response);
    }

}
