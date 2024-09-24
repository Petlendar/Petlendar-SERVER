package account.domain.account.business;

import account.domain.account.controller.model.TokenValidationRequest;
import account.domain.account.controller.model.TokenValidationResponse;
import account.domain.account.service.TokenService;
import global.annotation.Business;
import lombok.RequiredArgsConstructor;


@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;

    public TokenValidationResponse tokenValidation(TokenValidationRequest token) {
        Long userId = tokenService.validationToken(token.getTokenDto().getToken().substring(7));
        return TokenValidationResponse.builder().userId(userId).build();
    }
}
