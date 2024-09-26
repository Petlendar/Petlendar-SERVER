package pet.domain.pet.controller;

import global.annotation.UserSession;
import global.api.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.domain.pet.business.PetBusiness;
import pet.common.response.MessageResponse;
import pet.domain.pet.controller.model.detail.PetDetailResponse;
import pet.domain.pet.controller.model.register.PetRegisterRequest;
import pet.domain.pet.controller.model.register.PetRegisterResponse;
import pet.domain.pet.controller.model.update.PetUpdateRequest;
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

    @PostMapping("/unregister/{petId}")
    public Api<MessageResponse> unregister(@PathVariable Long petId, @UserSession User user) {
        MessageResponse response = petBusiness.unregister(petId, user.getId());
        return Api.OK(response);
    }

    @PostMapping("/update")
    public Api<MessageResponse> update(
        @RequestBody Api<PetUpdateRequest> petUpdateRequest,
        @UserSession User user
    ) {
        MessageResponse response = petBusiness.update(petUpdateRequest.getBody(), user.getId());
        return Api.OK(response);
    }

    @GetMapping("/{petId}")
    public Api<PetDetailResponse> getPet(@PathVariable Long petId, @UserSession User user) {
        PetDetailResponse response = petBusiness.getPetBy(petId, user.getId());
        return Api.OK(response);
    }

}
