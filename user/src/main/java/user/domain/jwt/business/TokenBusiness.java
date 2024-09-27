package user.domain.jwt.business;

import db.domain.token.RefreshTokenEntity;
import db.domain.user.UserEntity;
import global.annotation.Business;
import global.errorcode.ErrorCode;
import global.errorcode.TokenErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import user.common.exception.jwt.TokenException;
import user.common.exception.jwt.TokenSignatureException;
import user.domain.jwt.converter.TokenConverter;
import user.domain.jwt.model.TokenDto;
import user.domain.jwt.model.TokenResponse;
import user.domain.jwt.model.TokenValidationRequest;
import user.domain.jwt.model.TokenValidationResponse;
import user.domain.jwt.service.TokenService;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    @Transactional
    public TokenResponse issueToken(UserEntity userEntity) {

        if (userEntity == null) {
            throw new TokenException(ErrorCode.NULL_POINT);
        }

        Long userId = userEntity.getId();

        TokenDto accessToken = tokenService.issueAccessToken(userId);

        TokenDto refreshToken = tokenService.issueRefreshToken(userId);

        RefreshTokenEntity refreshTokenEntity = tokenConverter.toRefreshTokenEntity(
            userEntity.getId(), refreshToken.getToken());

        tokenService.deleteRefreshToken(userId);

        tokenService.saveRefreshToken(refreshTokenEntity);

        return tokenConverter.toResponse(accessToken, refreshToken);
    }

    public TokenDto reIssueAccessToken(String refreshToken) {
        if (refreshToken != null && refreshToken.startsWith("Bearer ")) { // 헤더 검증
            String token = refreshToken.substring(7);
            return tokenService.reIssueAccessToken(token);
        }
        throw new TokenSignatureException(TokenErrorCode.INVALID_TOKEN);
    }

    public TokenValidationResponse tokenValidation(TokenValidationRequest token) {
        Long userId = tokenService.validationToken(token.getTokenDto().getToken().substring(7));
        System.out.println(userId);
        return TokenValidationResponse.builder().userId(userId).build();
    }
}
