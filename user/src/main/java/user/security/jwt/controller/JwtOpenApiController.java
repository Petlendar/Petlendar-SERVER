package user.security.jwt.controller;

import global.api.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.security.jwt.business.TokenBusiness;
import user.security.jwt.model.TokenDto;
import user.security.jwt.model.TokenValidationRequest;
import user.security.jwt.model.TokenValidationResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/open-api/token")
public class JwtOpenApiController {

    private final TokenBusiness tokenBusiness;

    @PostMapping("/reissue")
    @Operation(summary = "[refreshToken 으로 accessToken 재발급]")
    public Api<TokenDto> reIssueAccessToken(
        @Parameter(hidden = true) @RequestHeader("Authorization") String refreshToken
    ){
        TokenDto response = tokenBusiness.reIssueAccessToken(refreshToken);
        return Api.OK(response);
    }

    @PostMapping()
    @Operation(summary = "[토큰 검증]")
    public Api<TokenValidationResponse> validationToken(
        @Parameter(hidden = true) @RequestBody TokenValidationRequest  tokenValidationRequest
    ){
        TokenValidationResponse response = tokenBusiness.tokenValidation(tokenValidationRequest);
        return Api.OK(response);
    }

}
