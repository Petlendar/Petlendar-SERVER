package user.domain.jwt.converter;

import db.domain.token.refreshtoken.RefreshTokenEntity;
import global.annotation.Converter;
import global.errorcode.ErrorCode;
import java.util.Objects;
import user.common.exception.jwt.TokenException;
import user.domain.jwt.model.TokenDto;
import user.domain.jwt.model.TokenResponse;

@Converter
public class TokenConverter {

    public RefreshTokenEntity toRefreshTokenEntity(Long userId, String refreshToken) {
        return RefreshTokenEntity.builder()
            .userId(userId)
            .refreshToken(refreshToken)
            .build();
    }

    public TokenResponse toResponse(TokenDto accessToken, TokenDto refreshToken) {

        Objects.requireNonNull(accessToken, ()->{
            throw new TokenException(ErrorCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken, ()->{
            throw new TokenException(ErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
            .accessToken(accessToken.getToken())
            .accessTokenExpiredAt(accessToken.getExpiredAt())
            .refreshToken(refreshToken.getToken())
            .refreshTokenExpiredAt(refreshToken.getExpiredAt())
            .build();
    }
}
