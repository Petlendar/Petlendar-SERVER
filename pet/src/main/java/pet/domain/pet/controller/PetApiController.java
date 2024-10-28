package pet.domain.pet.controller;

import global.annotation.UserSession;
import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
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
import pet.domain.pet.business.PetBusiness;
import pet.common.response.MessageResponse;
import pet.domain.pet.controller.model.detail.PetListResponse;
import pet.domain.pet.controller.model.detail.PetDetailResponse;
import pet.domain.pet.controller.model.register.PetRegisterRequest;
import pet.domain.pet.controller.model.register.PetRegisterResponse;
import pet.domain.pet.controller.model.update.PetUpdateRequest;
import pet.common.resolver.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pet")
public class PetApiController {

    private final PetBusiness petBusiness;

    @PostMapping()
    @Operation(summary = "[반려동물 등록]")
    public Api<PetRegisterResponse> register(
        @RequestBody @Valid Api<PetRegisterRequest> registerRequest,
        @Parameter(hidden = true) @UserSession User user
    ) {
        PetRegisterResponse response = petBusiness.register(registerRequest.getBody(), user.getId());
        return Api.OK(response);
    }

    @PostMapping("/unregister/{petId}")
    @Operation(summary = "[반려동물 삭제]")
    public Api<MessageResponse> unregister(
        @PathVariable Long petId,
        @Parameter(hidden = true) @UserSession User user
    ) {
        MessageResponse response = petBusiness.unregister(petId, user.getId());
        return Api.OK(response);
    }

    @PostMapping("/update")
    @Operation(summary = "[반려동물 수정]")
    public Api<MessageResponse> update(
        @RequestBody  @Valid Api<PetUpdateRequest> petUpdateRequest,
        @Parameter(hidden = true) @UserSession User user
    ) {
        MessageResponse response = petBusiness.update(petUpdateRequest.getBody(), user.getId());
        return Api.OK(response);
    }

    @GetMapping("/{petId}")
    @Operation(summary = "[반려동물 단일 조회]")
    public Api<PetDetailResponse> getPet(
        @PathVariable Long petId,
        @Parameter(hidden = true) @UserSession User user
    ) {
        PetDetailResponse response = petBusiness.getPetBy(petId, user.getId());
        return Api.OK(response);
    }

    @GetMapping()
    @Operation(summary = "[반려동물 목록 조회]")
    public Api<List<PetListResponse>> getPetList(@Parameter(hidden = true) @UserSession User user) {
        List<PetListResponse> response = petBusiness.getPetListBy(user.getId());
        return Api.OK(response);
    }

}
